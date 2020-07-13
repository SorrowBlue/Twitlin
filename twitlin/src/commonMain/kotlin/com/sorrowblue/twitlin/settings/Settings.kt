package com.sorrowblue.twitlin.settings

expect class Settings {
	fun getString(key: String, defValues: String): String
	fun getStringOrNull(key: String): String?
	fun getInt(key: String, defValues: Int): Int
	fun getIntOrNull(key: String): Int?
	fun getBoolean(key: String, defValues: Boolean): Boolean
	fun getBooleanOrNull(key: String): Boolean?
	fun getFloat(key: String, defValues: Float): Float
	fun getFloatOrNull(key: String): Float?
	fun getLong(key: String, defValues: Long): Long
	fun getLongOrNull(key: String): Long?
	fun getDouble(key: String, defValues: Double): Double
	fun getDoubleOrNull(key: String): Double?
	fun getStringSet(key: String, defValues: Set<String>): Set<String>
	fun getStringSetOrNull(key: String): Set<String>?
	fun contains(key: String): Boolean

	class Listener {
		fun remove()
	}

	fun addListener(key: String, callback: () -> Unit): Listener
	fun putDouble(key: String, value: Double)
	fun putStringSet(key: String, value: Set<String>)
	fun putLong(key: String, value: Long)
	fun putFloat(key: String, value: Float)
	fun putBoolean(key: String, value: Boolean)
	fun putInt(key: String, value: Int)
	fun putString(key: String, value: String)

	operator fun minusAssign(key: String)
	fun remove(key: String)

}
