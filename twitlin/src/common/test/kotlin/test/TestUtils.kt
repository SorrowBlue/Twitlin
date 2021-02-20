/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

internal expect object TestUtils {

    fun runBlocking(block: suspend () -> Unit)

    fun loadFile(path: String): String
}


