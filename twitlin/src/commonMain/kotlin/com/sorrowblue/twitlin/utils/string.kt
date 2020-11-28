package com.sorrowblue.twitlin.utils

import io.ktor.http.*

internal fun String.urlEncode() = encodeURLParameter()
