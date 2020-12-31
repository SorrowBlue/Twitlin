/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.client.Response

internal fun <T> Response<T>.testResult(): T? {
    onSuccess {
        Napier.d("onSuccess() = $it", tag = "Test")
    }.onError {
        Napier.e("onErrors() = $it", tag = "Test")
    }
    return getOrNull()
}
