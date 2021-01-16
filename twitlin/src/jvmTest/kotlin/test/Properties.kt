/*
 * (c) 2021 SorrowBlue.
 */

package test

import java.io.File
import java.io.FileInputStream
import java.util.Properties as JavaProperties

internal actual class Properties actual constructor(path: String) {

    private val properties =
        JavaProperties().apply { load(FileInputStream(File("src/commonTest/resources", path))) }

    actual fun getProperty(key: String): String? = properties.getProperty(key)

    actual fun getProperty(key: String, defaultValue: String): String =
        properties.getProperty(key, defaultValue) ?: defaultValue
}
