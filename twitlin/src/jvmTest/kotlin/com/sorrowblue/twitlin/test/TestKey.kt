package com.sorrowblue.twitlin.test

import java.io.File
import java.io.FileInputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

private const val INIT_FILE_PATH = "src/commonTest/resources/local.properties"

actual object TestKey {

	private val prop = Properties().apply {
		File(INIT_FILE_PATH).inputStream().use(this::load) }

	actual val API_KEY: String = prop.getProperty("API_KEY")
	actual val API_SECRET: String = prop.getProperty("API_SECRET")
	actual val ACCESS_TOKEN: String = prop.getProperty("ACCESS_TOKEN")
	actual val ACCESS_TOKEN_SECRET: String = prop.getProperty("ACCESS_TOKEN_SECRET")

}
