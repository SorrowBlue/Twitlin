/*
 * (c) 2021 SorrowBlue.
 */

package test

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlinx.coroutines.runBlocking as kotlinRunBlocking

internal actual object TestUtils {

    actual fun runBlocking(block: suspend () -> Unit) = kotlinRunBlocking { block() }

    @OptIn(ExperimentalPathApi::class)
    actual fun loadFile(path: String): String =
        Files.readAllLines(Path("src/commonTest/resources", path), StandardCharsets.UTF_8)
            .plus("").joinToString("\n")
}

