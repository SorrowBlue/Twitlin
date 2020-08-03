package com.sorrowblue.twitlin.settings

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import kotlinx.serialization.stringify
import java.util.prefs.Preferences

actual class Settings {

	private val pref = Preferences.userNodeForPackage(javaClass)

	actual fun contains(key: String) = pref.keys().contains(key)

	actual fun getString(key: String, defValue: String?) = pref.get(key, defValue)

	actual fun getStringSet(
		key: String,
		defValues: Set<String>?
	): Set<String>? =
		pref.get(key, null)?.let { Json.decodeFromString<List<String>>(it) }?.toSet() ?: defValues

	actual fun putString(key: String, value: String) = use { pref.put(key, value) }

	actual fun putStringSet(key: String, value: Set<String>) = use { it.put(key, Json.encodeToString(value.toList())) }

	actual fun remove(key: String) = use { it.remove(key) }

	private fun use(action: (Preferences) -> Unit) {
		kotlin.runCatching {
			action(pref)
			pref.flush()
		}
	}
}
