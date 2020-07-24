package com.sorrowblue.twitlin.net

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import io.ktor.http.encodeURLPath

internal fun String.urlEncode() = encodeURLPath()
		.replace("+", "%2B")
		.replace("!", "%21")
		.replace(",", "%2C")
		.replace("&", "%26")

internal val DateTimeTz.Companion.MIN get() = DateTime.EPOCH.local
