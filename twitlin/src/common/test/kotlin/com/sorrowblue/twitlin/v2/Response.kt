package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.v2.client.Response

fun <T : Any> Response<T>.testResult(): T? {
    onSuccess {
        println("onSuccess: $it")
    }.onError {
        println("onError: $it")
    }
    return dataOrNull()
}
