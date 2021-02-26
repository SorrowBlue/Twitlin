/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

internal actual object TestUtils {

    actual fun runBlocking(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }

    actual fun loadFile(path: String): String =
        nodeFS.readFileSync(nodePath.join(RESOURCE_ROOT, path), "utf-8") as String
}
