@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns the value of an option by its name
 * (Check the list of available options on https://core.telegram.org/tdlib/options.) Can be called before authorization
 *
 * @name - The name of the option
 */
suspend fun TdHandler.getOption(
    name: String? = null
) = sync(GetOption(name))

suspend fun TdHandler.getOptionOrNull(
    name: String? = null
) = syncOrNull(GetOption(name))

fun TdHandler.getOptionWith(
    name: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<OptionValue>.() -> Unit)? = null
) = send(GetOption(name), stackIgnore + 1, submit)

/**
 * Sets the value of an option
 * (Check the list of available options on https://core.telegram.org/tdlib/options.) Only writable options can be set
 * Can be called before authorization
 *
 * @name - The name of the option
 * @value - The new value of the option
 */
suspend fun TdHandler.setOption(
    name: String? = null,
    value: OptionValue? = null
){
    sync(SetOption(name, value))
}


suspend fun TdHandler.setOptionIgnoreException(
    name: String? = null,
    value: OptionValue? = null
){
    syncOrNull(SetOption(name, value))
}


fun TdHandler.setOptionWith(
    name: String? = null,
    value: OptionValue? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetOption(name, value), stackIgnore + 1, submit)
