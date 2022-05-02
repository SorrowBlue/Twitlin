package com.sorrowblue.twitlin.api.ktx

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromMap

@OptIn(ExperimentalSerializationApi::class)
internal inline fun <reified T : Any> Properties.decodeFromString(str: String): T {
    return str.split('&').associate {
        val params = it.split('=')
        if (params.size != 2) {
            throw IllegalStateException("\"$it\" is not parameter format.")
        }
        val value = params[1]
        params[0] to if (value == "true" || value == "false") {
            value.toBoolean()
        } else {
            value
        }
    }.let {
        decodeFromMap(it)
    }
}
