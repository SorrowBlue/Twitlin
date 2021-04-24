/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.v2.client.Response
import test.logger

internal fun <T : Any> Response<T>.testResult(): T? {
    onSuccess {
        logger.debug { "onSuccess() = $it" }
    }.onError {
        logger.debug { "onFailure() = $it" }
    }
    return dataOrNull()
}
