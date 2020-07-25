package com.sorrowblue.twitlin.net

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import io.ktor.http.encodeOAuth
import io.ktor.http.encodeURLParameter
import io.ktor.http.encodeURLPath

internal fun String.urlEncode() = this.encodeOAuth()
	.replace("+", "%2B")
	.replace("~", "%7E")
	.replace("%2A", "*")
	.replace("!", "%21")
	.replace(",", "%2C")
	.replace("&", "%26")

internal val DateTimeTz.Companion.MIN get() = DateTime.EPOCH.local
