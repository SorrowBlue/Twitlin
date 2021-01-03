/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.test

import kotlinx.coroutines.runBlocking

actual fun runTest(block: suspend () -> Unit) = runBlocking { block() }
