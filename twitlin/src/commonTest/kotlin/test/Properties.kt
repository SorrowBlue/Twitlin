/*
 * (c) 2021 SorrowBlue.
 */

package test

internal expect class Properties(path: String) {

    fun getProperty(key: String): String?

    fun getProperty(key: String, defaultValue: String): String
}
