/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.test

import java.io.File

actual fun loadJson(path: String): String = File("src/commonTest/resources$path").readText()
