package com.sorrowblue.twitlin.settings

import kotlinx.browser.localStorage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.get
import org.w3c.dom.set

actual class Settings {
	private val pref = localStorage
	actual fun contains(key: String): Boolean = pref[key] != null

	actual fun getString(key: String, defValue: String?): String? = pref[key] ?: defValue

	actual fun getStringSet(key: String, defValues: Set<String>?): Set<String>? =
		pref[key]?.let { Json.decodeFromString<List<String>>(it) }?.toSet() ?: defValues

	actual fun putStringSet(key: String, value: Set<String>) =
		pref.set(key, Json.encodeToString(value.toList()))

	actual fun putString(key: String, value: String) = pref.set(key, value)

	actual fun remove(key: String) = pref.removeItem(key)
}
