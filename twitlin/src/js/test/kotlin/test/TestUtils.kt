/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

internal actual object TestUtils {

    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit): dynamic =
        GlobalScope.promise { block(this) }
}
