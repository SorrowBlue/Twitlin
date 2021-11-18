package test

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise

internal actual object TestUtils {

    val testScope = MainScope()
    actual val testCoroutineContext: CoroutineContext = testScope.coroutineContext
    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit): dynamic = testScope.promise { this.block() }

}
