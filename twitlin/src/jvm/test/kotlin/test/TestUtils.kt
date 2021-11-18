package test

import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking as kotlinRunBlocking

internal actual object TestUtils {

    actual val testCoroutineContext: CoroutineContext =
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) =
        kotlinRunBlocking(testCoroutineContext) { this.block() }
}
