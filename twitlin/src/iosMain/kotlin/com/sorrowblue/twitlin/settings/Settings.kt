package com.sorrowblue.twitlin.settings

actual class Settings {
	actual fun contains(key: String): Boolean = false

	actual fun getString(key: String, defValue: String?): String? = null

	actual fun getStringSet(key: String, defValues: Set<String>?): Set<String>? = null

	actual fun putStringSet(key: String, value: Set<String>) {}

	actual fun putString(key: String, value: String) {}

	actual fun remove(key: String) {}
}
