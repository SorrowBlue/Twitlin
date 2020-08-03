package com.sorrowblue.twitlin.settings

actual class Settings {
	actual fun contains(key: String): Boolean {
		TODO("Not yet implemented")
	}

	actual fun getString(key: String, defValue: String?): String? {
		TODO("Not yet implemented")
	}

	actual fun getStringSet(
		key: String,
		defValues: Set<String>?,
	): Set<String>? {
		TODO("Not yet implemented")
	}

	actual fun putStringSet(key: String, value: Set<String>) {
	}

	actual fun putString(key: String, value: String) {
	}

	actual fun remove(key: String) {
	}
}
