package com.sorrowblue.twitlin.v2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.v2.client.Response

internal fun <T : Any> Response<T>.testResult(): T? {
    onSuccess {
        Napier.d("onSuccess() = $it", tag = "Test")
    }.onError {
        Napier.e("onFailure() = $it", tag = "Test")
    }
    return dataOrNull()
}
