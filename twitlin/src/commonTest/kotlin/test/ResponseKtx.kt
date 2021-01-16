/*
 * (c) 2021 SorrowBlue.
 */

package test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.client.Response

internal fun <T : Any> Response<T>.resultLog(): T? {
    onSuccess {
        Napier.d("onSuccess() = $it")
    }.onError {
        Napier.e("onErrors() = $it")
    }
    return dataOrNull()
}
