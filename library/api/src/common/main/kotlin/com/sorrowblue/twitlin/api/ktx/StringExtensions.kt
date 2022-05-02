package com.sorrowblue.twitlin.api.ktx

import io.ktor.http.encodeURLParameter

internal fun String.urlEncode() = encodeURLParameter()
