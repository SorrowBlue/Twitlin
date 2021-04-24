/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import com.sorrowblue.twitlin.client.Response

internal fun <T : Any> Response<T>.resultLog(): T? {
    onSuccess {
        logger.debug { "onSuccess() = $it" }
    }.onError {
        logger.error { "onErrors() = $it" }
    }
    return dataOrNull()
}
