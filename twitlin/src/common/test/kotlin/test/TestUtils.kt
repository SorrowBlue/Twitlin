/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.CoroutineScope

internal expect object TestUtils {

    fun runBlocking(block: suspend CoroutineScope.() -> Unit)
}
