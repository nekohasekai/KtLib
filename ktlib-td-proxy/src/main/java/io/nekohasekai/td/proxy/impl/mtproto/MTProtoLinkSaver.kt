package io.nekohasekai.td.proxy.impl.mtproto

import io.nekohasekai.td.proxy.saver.LinkSaver
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.net.URI

object MTProtoLinkSaver : LinkSaver<MTProtoProxy> {

    private fun String.addMTProtoParams(proxy: MTProtoProxy): String {

        val scheme = URI.create(this).scheme

        return replace("$scheme://", "https://").toHttpUrl().newBuilder().apply {

            addQueryParameter("server", proxy.server)
            addQueryParameter("port", "${proxy.port}")
            addQueryParameter("secret", proxy.secret)

        }.build().toString().replace("https://", "$scheme://")

    }

    override fun toLink(proxy: MTProtoProxy) = toLink("https", proxy)

    override fun toLink(protocol: String, proxy: MTProtoProxy): String {

        return if (protocol == "https") {

            "https://t.me/proxy".addMTProtoParams(proxy)

        } else {

            "$protocol://proxy".addMTProtoParams(proxy).replace("/?", "?")

        }

    }
}