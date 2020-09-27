@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.nekohasekai.ktlib.td.cli

import cn.hutool.core.codec.Base64
import cn.hutool.core.io.FileUtil
import cn.hutool.core.lang.Console
import cn.hutool.core.util.StrUtil
import io.nekohasekai.ktlib.core.*
import io.nekohasekai.ktlib.td.core.*
import io.nekohasekai.ktlib.td.core.raw.*
import io.nekohasekai.ktlib.td.extensions.*
import io.nekohasekai.ktlib.td.i18n.*
import io.nekohasekai.ktlib.td.utils.make
import io.nekohasekai.ktlib.td.utils.removeKeyboard
import kotlinx.coroutines.*
import org.apache.commons.lang3.SystemUtils
import org.yaml.snakeyaml.Yaml
import td.TdApi
import java.io.File
import kotlin.system.exitProcess

open class TdCli(tag: String = "", name: String = tag) : TdClient(tag, name) {

    lateinit var arguments: Array<String>

    fun launch(args: Array<String>) {

        TdLoader.tryLoad()

        arguments = args

        var ctn = false

        for (index in arguments.indices) {

            var argument = arguments[index]

            if (ctn) continue

            if (!argument.startsWith("--")) {

                onArgument(argument, arguments.shift().joinToString(" "))

            } else {

                argument = argument.substringAfter("--")

                var param: String? = null

                if (argument.contains("=")) {

                    param = argument.substringAfter("=")
                    argument = argument.substringBefore("=")

                } else if (arguments.size - 1 > index) {

                    ctn = true
                    param = arguments[index + 1]

                }

                onArgument(argument, param)

            }

        }

    }

    val config = HashMap<String, Any>()

    open val readConfigFromEnvironment = true

    fun stringConfig(key: String) = config[key]?.toString() ?: if (readConfigFromEnvironment) {
        SystemUtils.getEnvironmentVariable(key, null)
    } else null

    fun listConfig(key: String) = (config[key] as? List<*>)?.map { it.toString() } ?: if (readConfigFromEnvironment) {
        SystemUtils.getEnvironmentVariable(key, null)?.split(" ")
    } else null

    fun stringConfig(key: String, default: Boolean = false) = (config[key]?.toString()
            ?: if (readConfigFromEnvironment) {
                SystemUtils.getEnvironmentVariable(key, "$default")
            } else null)?.toUpperCase() == "true"

    fun intConfig(key: String) = runCatching {
        config[key]?.toString()?.toInt() ?: if (readConfigFromEnvironment) {
            SystemUtils.getEnvironmentVariable(key, null)?.toInt()
        } else null
    }.onFailure {
        defaultLog.warn(">> Invalid integer config item $key", it)
    }.getOrNull()

    fun longConfig(key: String) = runCatching {
        config[key]?.toString()?.toLong() ?: if (readConfigFromEnvironment) {
            SystemUtils.getEnvironmentVariable(key, null)?.toLong()
        } else null
    }.onFailure {
        defaultLog.warn(">> Invalid long config item $key", it)
    }.getOrNull()

    private val botTokenInEnv get() = stringConfig("BOT_TOKEN")
    private val binlogInEnv get() = stringConfig("BINLONG")
    override val defaultLang get() = stringConfig("BOT_LANG")

    var dataDir = File("data").canonicalFile
    var cacheDir = File("cache").canonicalFile

    open fun onArgument(argument: String, value: String?) {

        if (::configFile.isInitialized && argument == "config") {

            if (value == null) {

                defaultLog.warn(">> Missing config path")

                exitProcess(100)

            }

            val configFile = File(value).canonicalFile

            if (!configFile.isFile) {

                defaultLog.error(">> Config file ${configFile.path} not exists!")

                exitProcess(100)

            }

            this.configFile = configFile

        } else if (argument == "export") {

            val fileName = value ?: "binlog.txt"

            val binlogFile = File(options.databaseDirectory, if (options.useTestDc) "td_test.binlog" else "td.binlog")

            val authorized = binlogFile.isFile && runBlocking {

                onLoad()

                val exportClient = object : TdClient() {

                    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) {

                        super.onAuthorizationState(authorizationState)

                        if (authorizationState is TdApi.AuthorizationStateWaitPhoneNumber) {

                            authing = false

                        }

                    }

                }

                exportClient.options = options

                try {

                    exportClient.waitForAuth()

                } finally {

                    exportClient.waitForClose()

                }

            }

            if (!authorized) {

                clientLog.error("Unauthorized.")

                exitProcess(100)

            }

            var binlog = "-----BEGIN TDLIB BINLOG BLOCK-----\n"

            binlog += StrUtil.utf8Str(Base64.encode(binlogFile.readBytes(), true))

            binlog += "\n-----END TDLIB BINLOG BLOCK-----"

            val file = File(fileName).apply { parentFile.mkdirs() }

            file.writeText(binlog)

            clientLog.info(">> Saved to $fileName")

            exitProcess(0)

        }

    }

    open lateinit var configFile: File

    fun loadConfig() {

        val configFile = configFile

        if (configFile.isFile && configFile.canRead()) {

            try {

                val pmConfig = Yaml().loadAs(configFile.readText(), MutableMap::class.java)

                config.putAll(mapOf(* pmConfig.map { it.key!!.toString() to (it.value ?: "") }.toTypedArray()))

            } catch (e: Exception) {

                clientLog.error(e, "Parse config failed : ${configFile.path}")

                exitProcess(100)

            }

            onLoadConfig()

        }

    }

    fun loadLogLevel() {

        val logLevel = stringConfig("LOG_LEVEL")?.toUpperCase()

        if (logLevel != null) initLogLevel(logLevel)

    }

    fun loadDataDir() {

        val dataDirStr = stringConfig("DATA_DIR")

        if (!dataDirStr.isNullOrBlank()) {

            dataDir = File(dataDirStr)

            if (!dataDir.isDirectory && !dataDir.mkdirs()) {

                clientLog.error("Unable to create data directory: $dataDir")

                exitProcess(100)

            }

        }

        val cacheDirStr = stringConfig("CACHE_DIR")

        if (!cacheDirStr.isNullOrBlank()) {

            cacheDir = File(cacheDirStr)

            if (!cacheDir.isDirectory || cacheDir.mkdirs()) {

                clientLog.error("Unable to create cache directory: $cacheDir")

                exitProcess(100)

            }

        }

    }

    open fun onLoadConfig() {

        loadLogLevel()

        loadDataDir()

    }

    enum class LoginType { ALL, USER, BOT }

    open val loginType = LoginType.BOT

    override val skipSelfMessage by lazy { loginType == LoginType.BOT }

    override fun onLoad() {

        val apiId = intConfig("API_ID")

        if (apiId != null) {

            options apiId apiId

            val apiHash = stringConfig("API_HASH")

            if (apiHash.isNullOrBlank()) {

                clientLog.error("API_HASH undefined.")

                exitProcess(100)

            }

            options apiHash apiHash

        }

        options databaseDirectory dataDir.path
        options filesDirectory File(cacheDir, "files").path

    }

    override fun start() {

        val binlogInEnv = binlogInEnv

        if (binlogInEnv != null) {

            runCatching {

                val binlog = binlogInEnv
                        .substringAfter("-----BEGIN TDLIB BINLOG BLOCK-----")
                        .substringBefore("-----END TDLIB BINLOG BLOCK-----")
                        .let { Base64.decode(it) }

                val binlogFile = File(options.databaseDirectory,
                        if (options.useTestDc) "td_test.binlog" else "td.binlog")

                if (!binlogFile.isFile || !binlogFile.readBytes().contentEquals(binlog)) {

                    FileUtil.touch(binlogFile)

                    binlogFile.writeBytes(binlog)

                }

            }

        }

        super.start()

    }

    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) = withContext(Dispatchers.IO) {

        try {

            suspend fun inputPhone() {

                while (!stop) {

                    when (loginType) {
                        LoginType.ALL -> print(clientLocale.INPUT_PHONE_NUMBER_OR_BOT_TOKEN)
                        LoginType.USER -> print(clientLocale.INPUT_PHONE_NUMBER)
                        LoginType.BOT -> print(clientLocale.INPUT_BOT_TOKEN)
                    }

                    val phoneNumberOrBotToken = Console.input()

                    if ((loginType == LoginType.ALL && phoneNumberOrBotToken.contains(":")) || loginType == LoginType.BOT) {

                        try {

                            checkAuthenticationBotToken(phoneNumberOrBotToken)

                            botToken = phoneNumberOrBotToken

                            val botTkn = File("${sudo.options.databaseDirectory}/botToken")

                            botTkn.writeText(botToken)

                            break

                        } catch (e: TdException) {

                            println(clientLocale.INVALID_BOT_TOKEN.input(e))

                        }

                    } else {

                        try {

                            setAuthenticationPhoneNumber(phoneNumberOrBotToken)

                            break

                        } catch (e: TdException) {

                            println(clientLocale.INVALID_PHONE_NUMBER.input(e))

                        }

                    }

                }

            }

            if (authorizationState is TdApi.AuthorizationStateWaitPhoneNumber) {

                val botTokenInEnv = botTokenInEnv

                if (botTokenInEnv != null && loginType != LoginType.USER) {

                    try {

                        checkAuthenticationBotToken(botTokenInEnv)

                        botToken = botTokenInEnv

                        val botTkn = File("${sudo.options.databaseDirectory}/botToken")

                        botTkn.writeText(botToken)

                        return@withContext

                    } catch (e: TdException) {

                        println(clientLocale.INVALID_ENV_VARIABLE_BOT_TOKEN.input(e))

                    }

                }

                inputPhone()

            } else if (authorizationState is TdApi.AuthorizationStateWaitCode) {

                while (!stop) {

                    print(clientLocale.INPUT_AUTH_CODE)

                    val authenticationCode = Console.input()

                    if (authenticationCode == "resend") {

                        try {

                            resendAuthenticationCode()

                            println(clientLocale.AUTH_CODE_RESENT)

                        } catch (e: TdException) {

                            println(clientLocale.RESEND_FAILED.input(e))

                        }

                    } else if (authenticationCode == "reset") {

                        inputPhone()

                    } else {

                        try {

                            checkAuthenticationCode(authenticationCode)

                            break

                        } catch (e: TdException) {

                            // TODO: 语言文件

                            println("验证码无效 ($e), 使用 resend 重新发送, reset 重置手机号.")

                        }

                    }

                }

            } else if (authorizationState is TdApi.AuthorizationStateWaitPassword) {

                while (!stop) {

                    print("输入密码 (使用 destroy 删除账号.): ")

                    val authenticationPassword = Console.input()

                    if (authenticationPassword == "destroy") {

                        try {

                            deleteAccount("PASSWORD FORGETTED")

                            println("已删除")

                        } catch (e: TdException) {

                            println("重新发送失败: $e")

                        }

                    } else if (authenticationPassword == "reset") {

                        inputPhone()

                    } else {

                        try {

                            checkAuthenticationPassword(authenticationPassword)

                            break

                        } catch (e: TdException) {

                            println("密码错误 ($e), 使用 destroy 删除账号.")

                        }

                    }

                }

            } else if (authorizationState is TdApi.AuthorizationStateWaitRegistration) {

                while (!stop) {

                    print("输入名称以注册: ")

                    val name = Console.input()

                    try {

                        registerUser(name)

                        break

                    } catch (e: TdException) {

                        println("注册失败: $e")

                    }

                }

            } else if (authorizationState is TdApi.AuthorizationStateWaitOtherDeviceConfirmation) {

                println("请在其他设备确认登录.")

            } else if (authorizationState is TdApi.AuthorizationStateReady) {

                super.onAuthorizationState(authorizationState)

                if (isBot) {

                    val botTkn = File("${sudo.options.databaseDirectory}/botToken")

                    if (botTkn.isFile) botToken = botTkn.readText()

                }

                clientLog.info("Login User ${me.displayNameFormatted}")

            } else {

                super.onAuthorizationState(authorizationState)

            }

        } catch (e: NoSuchElementException) {

            clientLog.error("Login Failed")

            waitForClose()

            exitProcess(100)

        }

    }

    var botToken = ""

    override suspend fun onTermsOfService(termsOfServiceId: String, termsOfService: TdApi.TermsOfService) {

        acceptTermsOfServiceWith(termsOfServiceId) { onFailure = null }

    }

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (function == "cancel") {

            if (!message.fromPrivateOrDelete) return

            userCalled(userId, "nothing to cancel")

            sudo make localeFor(userId).NOTHING_TO_CANCEL withMarkup removeKeyboard() sendTo chatId

        } else if (!message.fromPrivate) {

            userCalled(userId, "undefined function, transfer to launch")

            onLaunch(userId, chatId, message)

        }

        finishEvent()

    }

    override suspend fun onSendCanceledMessage(userId: Int, chatId: Long) {

        val L = localeFor(userId)

        sudo make L.CANCELED withMarkup removeKeyboard() syncTo userId

    }

}