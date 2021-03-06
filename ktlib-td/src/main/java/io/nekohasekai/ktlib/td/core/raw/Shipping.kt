@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Sets the result of a shipping query
 * For bots only
 *
 * @shippingQueryId - Identifier of the shipping query
 * @shippingOptions - Available shipping options
 * @errorMessage - An error message, empty on success
 */
suspend fun TdHandler.answerShippingQuery(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null
){
    sync(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))
}


suspend fun TdHandler.answerShippingQueryIgnoreException(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null
){
    syncOrNull(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage))
}


fun TdHandler.answerShippingQueryWith(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AnswerShippingQuery(shippingQueryId, shippingOptions, errorMessage), stackIgnore + 1, submit)
