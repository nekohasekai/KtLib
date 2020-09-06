@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Creates a new call
 *
 * @userId - Identifier of the user to be called
 * @protocol - Description of the call protocols supported by the application
 * @isVideo - True, if a video call needs to be created
 */
suspend fun TdHandler.createCall(
        userId: Int,
        protocol: CallProtocol? = null,
        isVideo: Boolean
) = sync<CallId>(CreateCall(userId, protocol, isVideo))

suspend fun TdHandler.createCallOrNull(
        userId: Int,
        protocol: CallProtocol? = null,
        isVideo: Boolean
) = syncOrNull<CallId>(CreateCall(userId, protocol, isVideo))

fun TdHandler.createCallWith(
        userId: Int,
        protocol: CallProtocol? = null,
        isVideo: Boolean,
        stackIgnore: Int = 0,
        submit: (TdCallback<CallId>.() -> Unit)? = null
) = send(CreateCall(userId, protocol, isVideo), stackIgnore + 1, submit)

/**
 * Accepts an incoming call
 *
 * @callId - Call identifier
 * @protocol - Description of the call protocols supported by the application
 */
suspend fun TdHandler.acceptCall(
        callId: Int,
        protocol: CallProtocol? = null
) = sync<Ok>(AcceptCall(callId, protocol))

suspend fun TdHandler.acceptCallOrNull(
        callId: Int,
        protocol: CallProtocol? = null
) = syncOrNull<Ok>(AcceptCall(callId, protocol))

fun TdHandler.acceptCallWith(
        callId: Int,
        protocol: CallProtocol? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AcceptCall(callId, protocol), stackIgnore + 1, submit)

/**
 * Sends call signaling data
 *
 * @callId - Call identifier
 * @data - The data
 */
suspend fun TdHandler.sendCallSignalingData(
        callId: Int,
        data: ByteArray
) = sync<Ok>(SendCallSignalingData(callId, data))

suspend fun TdHandler.sendCallSignalingDataOrNull(
        callId: Int,
        data: ByteArray
) = syncOrNull<Ok>(SendCallSignalingData(callId, data))

fun TdHandler.sendCallSignalingDataWith(
        callId: Int,
        data: ByteArray,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendCallSignalingData(callId, data), stackIgnore + 1, submit)

/**
 * Discards a call
 *
 * @callId - Call identifier
 * @isDisconnected - True, if the user was disconnected
 * @duration - The call duration, in seconds
 * @isVideo - True, if the call was a video call
 * @connectionId - Identifier of the connection used during the call
 */
suspend fun TdHandler.discardCall(
        callId: Int,
        isDisconnected: Boolean,
        duration: Int,
        isVideo: Boolean,
        connectionId: Long
) = sync<Ok>(DiscardCall(callId, isDisconnected, duration, isVideo, connectionId))

suspend fun TdHandler.discardCallOrNull(
        callId: Int,
        isDisconnected: Boolean,
        duration: Int,
        isVideo: Boolean,
        connectionId: Long
) = syncOrNull<Ok>(DiscardCall(callId, isDisconnected, duration, isVideo, connectionId))

fun TdHandler.discardCallWith(
        callId: Int,
        isDisconnected: Boolean,
        duration: Int,
        isVideo: Boolean,
        connectionId: Long,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DiscardCall(callId, isDisconnected, duration, isVideo, connectionId), stackIgnore + 1, submit)

/**
 * Sends a call rating
 *
 * @callId - Call identifier
 * @rating - Call rating
 * @comment - An optional user comment if the rating is less than 5
 * @problems - List of the exact types of problems with the call, specified by the user
 */
suspend fun TdHandler.sendCallRating(
        callId: Int,
        rating: Int,
        comment: String? = null,
        problems: Array<CallProblem>
) = sync<Ok>(SendCallRating(callId, rating, comment, problems))

suspend fun TdHandler.sendCallRatingOrNull(
        callId: Int,
        rating: Int,
        comment: String? = null,
        problems: Array<CallProblem>
) = syncOrNull<Ok>(SendCallRating(callId, rating, comment, problems))

fun TdHandler.sendCallRatingWith(
        callId: Int,
        rating: Int,
        comment: String? = null,
        problems: Array<CallProblem>,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendCallRating(callId, rating, comment, problems), stackIgnore + 1, submit)

/**
 * Sends debug information for a call
 *
 * @callId - Call identifier
 * @debugInformation - Debug information in application-specific format
 */
suspend fun TdHandler.sendCallDebugInformation(
        callId: Int,
        debugInformation: String? = null
) = sync<Ok>(SendCallDebugInformation(callId, debugInformation))

suspend fun TdHandler.sendCallDebugInformationOrNull(
        callId: Int,
        debugInformation: String? = null
) = syncOrNull<Ok>(SendCallDebugInformation(callId, debugInformation))

fun TdHandler.sendCallDebugInformationWith(
        callId: Int,
        debugInformation: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendCallDebugInformation(callId, debugInformation), stackIgnore + 1, submit)
