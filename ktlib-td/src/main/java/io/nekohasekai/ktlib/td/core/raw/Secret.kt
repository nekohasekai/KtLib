@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a secret chat by its identifier
 * This is an offline request
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.getSecretChat(
    secretChatId: Int
) = sync(GetSecretChat(secretChatId))

suspend fun TdHandler.getSecretChatOrNull(
    secretChatId: Int
) = syncOrNull(GetSecretChat(secretChatId))

fun TdHandler.getSecretChatWith(
    secretChatId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<SecretChat>.() -> Unit)? = null
) = send(GetSecretChat(secretChatId), stackIgnore + 1, submit)

/**
 * Closes a secret chat, effectively transferring its state to secretChatStateClosed
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.closeSecretChat(
    secretChatId: Int
){
    sync(CloseSecretChat(secretChatId))
}


suspend fun TdHandler.closeSecretChatIgnoreException(
    secretChatId: Int
){
    syncOrNull(CloseSecretChat(secretChatId))
}


fun TdHandler.closeSecretChatWith(
    secretChatId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CloseSecretChat(secretChatId), stackIgnore + 1, submit)
