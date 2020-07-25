package com.sorrowblue.twitlin.settings

expect class Settings {
	fun contains(key: String): Boolean
	fun getString(key: String, defValue: String?): String?
	fun getStringSet(key: String, defValues: Set<String>?): Set<String>?
	fun putStringSet(key: String, value: Set<String>)
	fun putString(key: String, value: String)
	fun remove(key: String)
}
