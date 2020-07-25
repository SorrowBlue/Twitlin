package com.sorrowblue.twitlin.settings

actual class Settings {
	actual fun getString(key: String, defValue: String): String {
		TODO("Not yet implemented")
	}

	actual fun getStringOrNull(key: String): String? {
		TODO("Not yet implemented")
	}

	actual fun getInt(key: String, defValues: Int): Int {
		TODO("Not yet implemented")
	}

	actual fun getIntOrNull(key: String): Int? {
		TODO("Not yet implemented")
	}

	actual fun getBoolean(key: String, defValues: Boolean): Boolean {
		TODO("Not yet implemented")
	}

	actual fun getBooleanOrNull(key: String): Boolean? {
		TODO("Not yet implemented")
	}

	actual fun getFloat(key: String, defValues: Float): Float {
		TODO("Not yet implemented")
	}

	actual fun getFloatOrNull(key: String): Float? {
		TODO("Not yet implemented")
	}

	actual fun getLong(key: String, defValues: Long): Long {
		TODO("Not yet implemented")
	}

	actual fun getLongOrNull(key: String): Long? {
		TODO("Not yet implemented")
	}

	actual fun getDouble(key: String, defValues: Double): Double {
		TODO("Not yet implemented")
	}

	actual fun getDoubleOrNull(key: String): Double? {
		TODO("Not yet implemented")
	}

	actual fun getStringSet(
		key: String,
		defValues: Set<String>
	): Set<String> {
		TODO("Not yet implemented")
	}

	actual fun getStringSetOrNull(key: String): Set<String>? {
		TODO("Not yet implemented")
	}

	actual fun contains(key: String): Boolean {
		TODO("Not yet implemented")
	}

	actual class Listener {
		actual fun remove() {

		}
	}

	actual fun addListener(
		key: String,
		callback: () -> Unit
	): Listener {
		TODO("Not yet implemented")
	}

	actual fun putDouble(key: String, value: Double) {
	}

	actual fun putStringSet(key: String, value: Set<String>) {
	}

	actual fun putLong(key: String, value: Long) {
	}

	actual fun putFloat(key: String, value: Float) {
	}

	actual fun putBoolean(key: String, value: Boolean) {
	}

	actual fun putInt(key: String, value: Int) {
	}

	actual fun putString(key: String, value: String) {
	}

	actual operator fun minusAssign(key: String) {
	}

	actual fun remove(key: String) {
	}

}
