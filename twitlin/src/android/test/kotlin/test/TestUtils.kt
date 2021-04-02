/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking as kotlinRunBlocking

internal actual object TestUtils {

    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) =
        kotlinRunBlocking { block(this) }
}
