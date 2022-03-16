package test

import com.sorrowblue.twitlin.core.client.Response

internal fun <T : Any> Response<T>.resultLog(): T? {
    onSuccess {
        println("success: $rateLimitInfo, $it")
    }.onError {
        println("error: $rateLimitInfo, $it")
    }
    return dataOrNull()
}
