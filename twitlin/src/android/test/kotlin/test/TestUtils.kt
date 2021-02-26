/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.runBlocking as kotlinRunBlocking

internal actual object TestUtils {

    actual fun runBlocking(block: suspend () -> Unit) = kotlinRunBlocking { block() }

    actual fun loadFile(path: String): String {
        TODO()
    }
}
