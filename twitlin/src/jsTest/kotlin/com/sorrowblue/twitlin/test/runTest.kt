/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual fun runTest(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }
