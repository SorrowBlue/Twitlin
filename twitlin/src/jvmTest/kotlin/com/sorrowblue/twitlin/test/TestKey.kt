/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.test

import java.io.File
import java.util.Properties

actual object TestKey {

    private val prop =
        Properties().apply { load(File("src/commonTest/resources/local.properties").inputStream()) }

    actual val API_KEY: String = prop.getProperty("API_KEY")
    actual val API_SECRET: String = prop.getProperty("API_SECRET")
    actual val ACCESS_TOKEN: String = prop.getProperty("ACCESS_TOKEN")
    actual val ACCESS_TOKEN_SECRET: String = prop.getProperty("ACCESS_TOKEN_SECRET")
}
