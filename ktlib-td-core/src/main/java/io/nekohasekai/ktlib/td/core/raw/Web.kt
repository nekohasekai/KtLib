@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import io.nekohasekai.ktlib.td.core.TdCallback
import io.nekohasekai.ktlib.td.core.TdHandler
import td.TdApi.*

/**
 * Returns a web page preview by the text of the message
 * Do not call this function too often
 * Returns a 404 error if the web page has no preview
 *
 * @text - Message text with formatting
 */
suspend fun TdHandler.getWebPagePreview(
        text: FormattedText? = null
) = sync<WebPage>(GetWebPagePreview(text))

suspend fun TdHandler.getWebPagePreviewOrNull(
        text: FormattedText? = null
) = syncOrNull<WebPage>(GetWebPagePreview(text))

fun TdHandler.getWebPagePreviewWith(
        text: FormattedText? = null,
        stackIgnore: Int = 0,
        submit: (TdCallback<WebPage>.() -> Unit)? = null
) = send(GetWebPagePreview(text), stackIgnore + 1, submit)

/**
 * Returns an instant view version of a web page if available
 * Returns a 404 error if the web page has no instant view page
 *
 * @url - The web page URL
 * @forceFull - If true, the full instant view for the web page will be returned
 */
suspend fun TdHandler.getWebPageInstantView(
        url: String? = null,
        forceFull: Boolean
) = sync<WebPageInstantView>(GetWebPageInstantView(url, forceFull))

suspend fun TdHandler.getWebPageInstantViewOrNull(
        url: String? = null,
        forceFull: Boolean
) = syncOrNull<WebPageInstantView>(GetWebPageInstantView(url, forceFull))

fun TdHandler.getWebPageInstantViewWith(
        url: String? = null,
        forceFull: Boolean,
        stackIgnore: Int = 0,
        submit: (TdCallback<WebPageInstantView>.() -> Unit)? = null
) = send(GetWebPageInstantView(url, forceFull), stackIgnore + 1, submit)

/**
 * Disconnects website from the current user's Telegram account
 *
 * @websiteId - Website identifier
 */
suspend fun TdHandler.disconnectWebsite(
        websiteId: Long
) = sync<Ok>(DisconnectWebsite(websiteId))

suspend fun TdHandler.disconnectWebsiteOrNull(
        websiteId: Long
) = syncOrNull<Ok>(DisconnectWebsite(websiteId))

fun TdHandler.disconnectWebsiteWith(
        websiteId: Long,
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DisconnectWebsite(websiteId), stackIgnore + 1, submit)

/**
 * Disconnects all websites from the current user's Telegram account
 */
suspend fun TdHandler.disconnectAllWebsites() = sync<Ok>(DisconnectAllWebsites())

suspend fun TdHandler.disconnectAllWebsitesOrNull() = syncOrNull<Ok>(DisconnectAllWebsites())

fun TdHandler.disconnectAllWebsitesWith(
        stackIgnore: Int = 0,
        submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DisconnectAllWebsites(), stackIgnore + 1, submit)
