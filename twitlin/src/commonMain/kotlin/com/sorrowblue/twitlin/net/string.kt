package com.sorrowblue.twitlin.net

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz

internal expect fun String.urlEncode(): String

internal val DateTimeTz.Companion.MIN get() = DateTime.EPOCH.local
