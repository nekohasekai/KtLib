@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Does nothing
 * For testing only
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdHandler.testCallEmpty() = sync<Ok>(TestCallEmpty())

suspend fun TdHandler.testCallEmptyOrNull() = syncOrNull<Ok>(TestCallEmpty())

fun TdHandler.testCallEmptyWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TestCallEmpty(), stackIgnore + 1, submit)

/**
 * Returns the received string
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - String to return
 */
suspend fun TdHandler.testCallString(
        x: String? = null
) = sync<TestString>(TestCallString(x))

suspend fun TdHandler.testCallStringOrNull(
        x: String? = null
) = syncOrNull<TestString>(TestCallString(x))

fun TdHandler.testCallStringWith(
        x: String? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestString>.() -> Unit)? = null
) = send(TestCallString(x), stackIgnore + 1, submit)

/**
 * Returns the received bytes
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Bytes to return
 */
suspend fun TdHandler.testCallBytes(
        x: ByteArray
) = sync<TestBytes>(TestCallBytes(x))

suspend fun TdHandler.testCallBytesOrNull(
        x: ByteArray
) = syncOrNull<TestBytes>(TestCallBytes(x))

fun TdHandler.testCallBytesWith(
        x: ByteArray,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestBytes>.() -> Unit)? = null
) = send(TestCallBytes(x), stackIgnore + 1, submit)

/**
 * Returns the received vector of numbers
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of numbers to return
 */
suspend fun TdHandler.testCallVectorInt(
        x: IntArray
) = sync<TestVectorInt>(TestCallVectorInt(x))

suspend fun TdHandler.testCallVectorIntOrNull(
        x: IntArray
) = syncOrNull<TestVectorInt>(TestCallVectorInt(x))

fun TdHandler.testCallVectorIntWith(
        x: IntArray,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestVectorInt>.() -> Unit)? = null
) = send(TestCallVectorInt(x), stackIgnore + 1, submit)

/**
 * Returns the received vector of objects containing a number
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of objects to return
 */
suspend fun TdHandler.testCallVectorIntObject(
        x: Array<TestInt>
) = sync<TestVectorIntObject>(TestCallVectorIntObject(x))

suspend fun TdHandler.testCallVectorIntObjectOrNull(
        x: Array<TestInt>
) = syncOrNull<TestVectorIntObject>(TestCallVectorIntObject(x))

fun TdHandler.testCallVectorIntObjectWith(
        x: Array<TestInt>,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestVectorIntObject>.() -> Unit)? = null
) = send(TestCallVectorIntObject(x), stackIgnore + 1, submit)

/**
 * Returns the received vector of strings
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of strings to return
 */
suspend fun TdHandler.testCallVectorString(
        x: Array<String>
) = sync<TestVectorString>(TestCallVectorString(x))

suspend fun TdHandler.testCallVectorStringOrNull(
        x: Array<String>
) = syncOrNull<TestVectorString>(TestCallVectorString(x))

fun TdHandler.testCallVectorStringWith(
        x: Array<String>,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestVectorString>.() -> Unit)? = null
) = send(TestCallVectorString(x), stackIgnore + 1, submit)

/**
 * Returns the received vector of objects containing a string
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of objects to return
 */
suspend fun TdHandler.testCallVectorStringObject(
        x: Array<TestString>
) = sync<TestVectorStringObject>(TestCallVectorStringObject(x))

suspend fun TdHandler.testCallVectorStringObjectOrNull(
        x: Array<TestString>
) = syncOrNull<TestVectorStringObject>(TestCallVectorStringObject(x))

fun TdHandler.testCallVectorStringObjectWith(
        x: Array<TestString>,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestVectorStringObject>.() -> Unit)? = null
) = send(TestCallVectorStringObject(x), stackIgnore + 1, submit)

/**
 * Returns the squared received number
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Number to square
 */
suspend fun TdHandler.testSquareInt(
        x: Int
) = sync<TestInt>(TestSquareInt(x))

suspend fun TdHandler.testSquareIntOrNull(
        x: Int
) = syncOrNull<TestInt>(TestSquareInt(x))

fun TdHandler.testSquareIntWith(
        x: Int,
        stackIgnore: Int = 0,
        submit: (TdCallback<TestInt>.() -> Unit)? = null
) = send(TestSquareInt(x), stackIgnore + 1, submit)

/**
 * Sends a simple network request to the Telegram servers
 * For testing only
 * Can be called before authorization
 */
suspend fun TdHandler.testNetwork() = sync<Ok>(TestNetwork())

suspend fun TdHandler.testNetworkOrNull() = syncOrNull<Ok>(TestNetwork())

fun TdHandler.testNetworkWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TestNetwork(), stackIgnore + 1, submit)

/**
 * Sends a simple network request to the Telegram servers via proxy
 * For testing only
 * Can be called before authorization
 *
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @type - Proxy type
 * @dcId - Identifier of a datacenter, with which to test connection
 * @timeout - The maximum overall timeout for the request
 */
suspend fun TdHandler.testProxy(
        server: String? = null,
        port: Int,
        type: ProxyType? = null,
        dcId: Int,
        timeout: Double
) = sync<Ok>(TestProxy(server, port, type, dcId, timeout))

suspend fun TdHandler.testProxyOrNull(
        server: String? = null,
        port: Int,
        type: ProxyType? = null,
        dcId: Int,
        timeout: Double
) = syncOrNull<Ok>(TestProxy(server, port, type, dcId, timeout))

fun TdHandler.testProxyWith(
        server: String? = null,
        port: Int,
        type: ProxyType? = null,
        dcId: Int,
        timeout: Double,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TestProxy(server, port, type, dcId, timeout), stackIgnore + 1, submit)

/**
 * Forces an updates.getDifference call to the Telegram servers
 * For testing only
 */
suspend fun TdHandler.testGetDifference() = sync<Ok>(TestGetDifference())

suspend fun TdHandler.testGetDifferenceOrNull() = syncOrNull<Ok>(TestGetDifference())

fun TdHandler.testGetDifferenceWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TestGetDifference(), stackIgnore + 1, submit)

/**
 * Does nothing and ensures that the Update object is used
 * For testing only
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdHandler.testUseUpdate() = sync<Update>(TestUseUpdate())

suspend fun TdHandler.testUseUpdateOrNull() = syncOrNull<Update>(TestUseUpdate())

fun TdHandler.testUseUpdateWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Update>.() -> Unit)? = null
) = send(TestUseUpdate(), stackIgnore + 1, submit)

/**
 * Returns the specified error and ensures that the Error object is used
 * For testing only
 * Can be called synchronously
 *
 * @error - The error to be returned
 */
suspend fun TdHandler.testReturnError(
        error: Error? = null
) = sync<Error>(TestReturnError(error))

suspend fun TdHandler.testReturnErrorOrNull(
        error: Error? = null
) = syncOrNull<Error>(TestReturnError(error))

fun TdHandler.testReturnErrorWith(
        error: Error? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<Error>.() -> Unit)? = null
) = send(TestReturnError(error), stackIgnore + 1, submit)
