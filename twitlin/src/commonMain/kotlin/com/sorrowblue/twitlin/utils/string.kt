package com.sorrowblue.twitlin.utils

import io.ktor.http.encodeURLParameter

internal fun String.urlEncode() = encodeURLParameter()
