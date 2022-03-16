package com.sorrowblue.twitlin.api.v2

import com.sorrowblue.twitlin.api.v2.client.Response

fun <T : Any> Response<T>.testResult(): T? {
    onSuccess {
        println("onSuccess=$it")
    }.onError {
        it.errors.forEach {
            println("onError= title: ${it.title}, type: ${it.type}")
        }
    }
    return dataOrNull()
}
