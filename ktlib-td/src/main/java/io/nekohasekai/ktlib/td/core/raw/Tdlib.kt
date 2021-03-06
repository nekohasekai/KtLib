@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Sets the parameters for TDLib initialization
 * Works only when the current authorization state is authorizationStateWaitTdlibParameters
 *
 * @parameters - Parameters
 */
suspend fun TdHandler.setTdlibParameters(
    parameters: TdlibParameters? = null
){
    sync(SetTdlibParameters(parameters))
}


suspend fun TdHandler.setTdlibParametersIgnoreException(
    parameters: TdlibParameters? = null
){
    syncOrNull(SetTdlibParameters(parameters))
}


fun TdHandler.setTdlibParametersWith(
    parameters: TdlibParameters? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetTdlibParameters(parameters), stackIgnore + 1, submit)
