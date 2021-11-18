package test

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

internal expect object TestUtils {

    fun runBlocking(block: suspend CoroutineScope.() -> Unit)

    val testCoroutineContext: CoroutineContext
}
