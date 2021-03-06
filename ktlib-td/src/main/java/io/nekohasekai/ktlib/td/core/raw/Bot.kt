@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Informs the server about the number of pending bot updates if they haven't been processed for a long time
 * For bots only
 *
 * @pendingUpdateCount - The number of pending updates
 * @errorMessage - The last error message
 */
suspend fun TdHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int,
    errorMessage: String? = null
){
    sync(SetBotUpdatesStatus(pendingUpdateCount, errorMessage))
}


suspend fun TdHandler.setBotUpdatesStatusIgnoreException(
    pendingUpdateCount: Int,
    errorMessage: String? = null
){
    syncOrNull(SetBotUpdatesStatus(pendingUpdateCount, errorMessage))
}


fun TdHandler.setBotUpdatesStatusWith(
    pendingUpdateCount: Int,
    errorMessage: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetBotUpdatesStatus(pendingUpdateCount, errorMessage), stackIgnore + 1, submit)
