@file:Suppress("unused", "RedundantSuspendModifier")

package io.nekohasekai.ktlib.td.core

import io.nekohasekai.ktlib.core.toLinkedList
import io.nekohasekai.ktlib.td.core.persists.TdPersist
import io.nekohasekai.ktlib.td.core.raw.AbsEvents
import io.nekohasekai.ktlib.td.extensions.fromPrivate
import io.nekohasekai.ktlib.td.extensions.textOrCaption
import io.nekohasekai.ktlib.td.utils.delete
import td.TdApi
import td.TdApi.*
import java.util.*

open class TdHandler : AbsEvents {

    private lateinit var _client: TdClient

    open val sudo get() = _client
    open val me get() = sudo.me
    open val fullInfo get() = sudo.fullInfo
    val isBot get() = me.type is UserTypeBot
    open val clientLog get() = sudo.clientLog

    open fun onLoad(client: TdClient) {

        _client = client

        onLoad()

    }

    open fun onLoad() = Unit

    open suspend fun gc() = Unit
    open suspend fun saveCache() = Unit

    open suspend fun beforeLogin() = Unit
    open suspend fun onLogin() = Unit

    open suspend fun onLogout() = Unit

    open suspend fun onDestroy() = Unit

    open fun <T : Object> send(
        function: TdApi.Function<T>,
        stackIgnore: Int = 0,
        submit: (TdCallback<T>.() -> Unit)? = null
    ) = sudo.send(function, stackIgnore, submit)

    open infix fun sendRaw(function: TdApi.Function<*>) = sudo.sendRaw(function)

    open suspend infix fun <T : Object> sync(function: TdApi.Function<T>): T = sudo.sync(function)

    suspend infix fun <T : Object> syncOrNull(function: TdApi.Function<T>): T? {

        return try {

            sudo.sync(function)

        } catch (ex: TdException) {

            null

        }

    }

    suspend infix fun syncUnit(function: TdApi.Function<out Object>) {
        sudo.sync(function)
    }

    val Message.delete get() = DeleteMessages(chatId, longArrayOf(id), true)
    val Message.deleteForSelf get() = DeleteMessages(chatId, longArrayOf(id), false)

    inline fun <reified T : TdHandler> findHandler(): T {

        sudo.handlers.toLinkedList().forEach {

            @Suppress("UNCHECKED_CAST")
            if (T::class.java.isInstance(it)) return it as T

        }

        error("Handler ${T::class.java.name} not found !")

    }

    fun Message.parseFunction(): TdFunction? {

        var param = textOrCaption ?: return null

        run fn@{

            sudo.funPrefix.forEach {

                if (!param.startsWith(it)) return@forEach

                param = param.substring(it.length)

                return@fn

            }

            return null

        }

        var function = if (!param.contains(' ')) {

            param.also {

                param = ""

            }

        } else {

            param.substringBefore(' ').also {

                param = param.substringAfter(' ')

            }

        }

        val validSuffix = "@${me.username}"

        if (function.endsWith(validSuffix)) {

            function = function.substring(0, function.length - validSuffix.length)

        }

        val params = if (param.isBlank()) listOf() else {

            var paramNew = param
            while (paramNew.contains("  ")) paramNew = paramNew.replace("  ", " ")
            paramNew.split(' ')

        }

        return TdFunction(function, param, params)

    }

    val Collection<Message>.deleteAll: List<DeleteMessages>
        get() {

            val messages = HashMap<Long, LinkedList<Message>>()

            forEach {

                var list = messages[it.chatId]

                if (list == null) {

                    list = LinkedList()

                    messages[it.chatId] = list

                }

                list.add(it)

            }

            return messages.map { (chatId, chatMessages) ->

                DeleteMessages(chatId, chatMessages.map { it.id }.toLongArray(), true)

            }

        }

    val Collection<Message>.deleteAllForSelf: List<DeleteMessages>
        get() {

            val messages = HashMap<Long, LinkedList<Message>>()

            forEach {

                var list = messages[it.chatId]

                if (list == null) {

                    list = LinkedList()

                    messages[it.chatId] = list

                }

                list.add(it)

            }

            return messages.map { (chatId, chatMessages) ->

                DeleteMessages(chatId, chatMessages.map { it.id }.toLongArray(), false)

            }

        }

    class Finish : RuntimeException("Finish Event")

    open fun finishEvent(): Nothing = throw Finish()

    class FinishWithDelay(val delay: Long) : RuntimeException("Finish And Delay")

    open fun finishWithDelay(delay: Long = 1000L): Nothing = throw FinishWithDelay(delay)

    val Message.fromPrivateOrDelete: Boolean
        get() {

            return if (fromPrivate) true else {

                sudo delete this

                false

            }

        }

    open fun initFunction(vararg functions: String) {

        functions.forEach { function ->

            sudo.functions.put(function, this)?.apply {

                error("function name already used by $this.")

            }

        }

    }

    open fun initData(dataId: Int) {

        sudo.callbackQueries.put(dataId, this)?.apply {

            error("data id already used by $this.")

        }

    }

    open fun initPersist(persistId: Int) {

        sudo.persistHandlers.put(persistId, this)?.apply {

            error("persist id already used by $this.")

        }

    }

    open fun initStartPayload(payload: String) {

        sudo.payloads.put(payload, this)?.apply {

            error("payload prefix already used by $this.")

        }

    }

    open fun writePersist(
        userId: Int,
        persistId: Int,
        subId: Int,
        vararg data: Any?,
        allowFunction: Boolean = false,
        allowCancel: Boolean = true,
        dontSave: Boolean = false
    ) {

        sudo.persists.save(
            TdPersist(
                userId,
                persistId,
                subId,
                arrayOf(* data),
                allowFunction,
                allowCancel,
                dontSave = dontSave
            )
        )

    }

    suspend infix fun removePersist(userId: Int) {

        sudo.persists.remove(userId)

    }

    open suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = Unit

    open suspend fun onFunction(
        userId: Int,
        chatId: Long,
        message: Message,
        function: String,
        param: String,
        params: Array<String>
    ) = Unit

    open suspend fun onUndefinedFunction(
        userId: Int,
        chatId: Long,
        message: Message,
        function: String,
        param: String,
        params: Array<String>
    ) = Unit

    open suspend fun onStartPayload(
        userId: Int,
        chatId: Long,
        message: Message,
        payload: String,
        param: String,
        params: Array<String>
    ) = Unit

    open suspend fun onUndefinedPayload(
        userId: Int,
        chatId: Long,
        message: Message,
        payload: String,
        param: String,
        params: Array<String>
    ) = Unit

    open suspend fun onNewCallbackQuery(
        userId: Int,
        chatId: Long,
        messageId: Long,
        queryId: Long,
        data: Array<ByteArray>
    ) = Unit

    open suspend fun onNewInlineCallbackQuery(
        userId: Int,
        inlineMessageId: String,
        queryId: Long,
        data: Array<ByteArray>
    ) = Unit

    open suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int, data: Array<Any?>) = Unit

    open suspend fun onPersistFunction(
        userId: Int,
        chatId: Long,
        message: Message,
        subId: Int,
        data: Array<Any?>,
        function: String,
        param: String,
        params: Array<String>
    ) {

        rejectFunction()

    }

    open suspend fun onPersistRemove(userId: Int, chatId: Long, subId: Int, data: Array<Any?>) = Unit

    open suspend fun onPersistRemoveOrCancel(userId: Int, chatId: Long, subId: Int, data: Array<Any?>) = Unit
    open suspend fun onPersistCancel(userId: Int, chatId: Long, message: Message, subId: Int, data: Array<Any?>) = Unit
    open suspend fun onPersistTimeout(userId: Int, chatId: Long, subId: Int, data: Array<Any?>) = Unit

    open suspend fun onSendCanceledMessage(userId: Int, chatId: Long) {

        sudo.onSendCanceledMessage(userId, chatId)

    }

    open suspend fun onSendTimeoutMessage(userId: Int, chatId: Long) {

        sudo.onSendTimeoutMessage(userId, chatId)

    }

    class Reject : RuntimeException("Reject Function")

    fun rejectFunction(): Nothing = throw Reject()

}