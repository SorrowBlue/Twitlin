/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.DebugAntilog

internal actual val defaultAntilog: Antilog = DebugAntilog(Twitlin.TAG)
