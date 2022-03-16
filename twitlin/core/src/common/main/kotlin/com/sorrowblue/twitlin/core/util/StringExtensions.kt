package com.sorrowblue.twitlin.core.util

import io.ktor.http.encodeURLParameter

internal fun String.urlEncode() = encodeURLParameter()
