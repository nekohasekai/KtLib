@file:Suppress("unused")

package io.nekohasekai.ktlib.core

import cn.hutool.core.date.DateUtil
import cn.hutool.core.lang.Console
import cn.hutool.core.lang.Dict
import cn.hutool.core.util.StrUtil
import cn.hutool.log.GlobalLogFactory
import cn.hutool.log.LogFactory
import cn.hutool.log.dialect.console.ConsoleLog
import cn.hutool.log.level.Level

object KLogFactory : LogFactory("Neko Log") {

    override fun createLog(name: String?) = if (name != null) mkLog(name) else defaultLog

    override fun createLog(clazz: Class<*>?) = if (clazz != null) mkLog(clazz.simpleName).apply {
        when (clazz) {
            GlobalLogFactory::class.java -> logLevel = Level.INFO
        }
    } else defaultLog
}

var LOG_LEVEL = Level.INFO

val defaultLog = mkLog("")

fun mkLog(name: String) = KLog(name)

class KLog(name: String) : ConsoleLog(name) {

    companion object {
        init {
            LogFactory.setCurrentLogFactory(KLogFactory)
        }
    }

    lateinit var logLevel: Level

    override fun log(fqcn: String, level: Level, t: Throwable?, format: String?, vararg arguments: Any) {

        val logLevel = if (::logLevel.isInitialized) logLevel else LOG_LEVEL

        if (logLevel > level) return

        var logMsg = if (t != null) {

            var logWithExc = if (t.message != format) format?.input(arguments) ?: "" + "\n" else ""

            logWithExc += t.parse()

            logWithExc

        } else {

            format?.input(arguments) ?: "null"

        }

        val dict = Dict.create().set("date", DateUtil.now()).set("level", level.toString()).set("name", name)
            .set("msg", logMsg)

        val logFormat = if (name.isBlank()) "[{level}] {msg}" else "[{level}] [{name}] {msg}"

        logMsg = StrUtil.format(logFormat, dict)

        if (level.ordinal >= Level.WARN.ordinal) {

            Console.error(logMsg)

        } else {

            Console.log(logMsg)

        }

    }

}