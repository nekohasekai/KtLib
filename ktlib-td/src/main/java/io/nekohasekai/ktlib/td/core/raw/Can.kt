@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Checks whether the current session can be used to transfer a chat ownership to another user
 */
suspend fun TdHandler.canTransferOwnership() = sync(CanTransferOwnership())

suspend fun TdHandler.canTransferOwnershipOrNull() = syncOrNull(CanTransferOwnership())

fun TdHandler.canTransferOwnershipWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<CanTransferOwnershipResult>.() -> Unit)? = null
) = send(CanTransferOwnership(), stackIgnore + 1, submit)

/**
 * Stops the downloading of a file
 * If a file has already been downloaded, does nothing
 *
 * @fileId - Identifier of a file to stop downloading
 * @onlyIfPending - Pass true to stop downloading only if it hasn't been started, i.e
 *                  Request hasn't been sent to server
 */
suspend fun TdHandler.cancelDownloadFile(
    fileId: Int,
    onlyIfPending: Boolean
){
    sync(CancelDownloadFile(fileId, onlyIfPending))
}


suspend fun TdHandler.cancelDownloadFileIgnoreException(
    fileId: Int,
    onlyIfPending: Boolean
){
    syncOrNull(CancelDownloadFile(fileId, onlyIfPending))
}


fun TdHandler.cancelDownloadFileWith(
    fileId: Int,
    onlyIfPending: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CancelDownloadFile(fileId, onlyIfPending), stackIgnore + 1, submit)

/**
 * Stops the uploading of a file
 * Supported only for files uploaded by using uploadFile
 * For other files the behavior is undefined
 *
 * @fileId - Identifier of the file to stop uploading
 */
suspend fun TdHandler.cancelUploadFile(
    fileId: Int
){
    sync(CancelUploadFile(fileId))
}


suspend fun TdHandler.cancelUploadFileIgnoreException(
    fileId: Int
){
    syncOrNull(CancelUploadFile(fileId))
}


fun TdHandler.cancelUploadFileWith(
    fileId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CancelUploadFile(fileId), stackIgnore + 1, submit)
