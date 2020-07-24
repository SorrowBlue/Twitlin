package com.sorrowblue.twitlin.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

actual class Settings(context: Context, name: String? = null) {

	private val pref: SharedPreferences =
		name?.let { context.getSharedPreferences(it, Context.MODE_PRIVATE) }
			?: PreferenceManager.getDefaultSharedPreferences(context)

	actual fun getString(key: String, defValues: String): String =
		pref.getString(key, defValues) ?: defValues

	actual fun getStringOrNull(key: String): String? =
		if (pref.contains(key)) pref.getString(key, "") else null

	actual fun getInt(key: String, defValues: Int): Int = pref.getInt(key, defValues)

	actual fun getIntOrNull(key: String): Int? =
		if (pref.contains(key)) pref.getInt(key, 0) else null

	actual fun getBoolean(key: String, defValues: Boolean): Boolean =
		pref.getBoolean(key, defValues)

	actual fun getBooleanOrNull(key: String): Boolean? =
		if (pref.contains(key)) pref.getBoolean(key, false) else null

	actual fun getFloat(key: String, defValues: Float): Float = pref.getFloat(key, defValues)

	actual fun getFloatOrNull(key: String): Float? =
		if (pref.contains(key)) pref.getFloat(key, 0f) else null

	actual fun getLong(key: String, defValues: Long): Long = pref.getLong(key, defValues)

	actual fun getLongOrNull(key: String): Long? =
		if (pref.contains(key)) pref.getLong(key, 0) else null

	actual fun getDouble(key: String, defValues: Double): Double =
		Double.fromBits(pref.getLong(key, defValues.toRawBits()))

	actual fun getDoubleOrNull(key: String): Double? =
		if (pref.contains(key)) Double.fromBits(pref.getLong(key, 0.0.toRawBits())) else null


	actual fun getStringSet(key: String, defValues: Set<String>): Set<String> =
		pref.getStringSet(key, defValues) ?: defValues

	actual fun getStringSetOrNull(key: String): Set<String>? =
		if (pref.contains(key)) pref.getStringSet(key, emptySet()) else null

	actual fun contains(key: String): Boolean = pref.contains(key)

	actual class Listener(
		private val pref: SharedPreferences,
		private val listener: SharedPreferences.OnSharedPreferenceChangeListener
	) {
		actual fun remove() {
			pref.unregisterOnSharedPreferenceChangeListener(listener)
		}
	}

	actual fun addListener(
		key: String,
		callback: () -> Unit
	): Listener {
		var prev = pref.all[key]
		val listener =
			SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, updatedKey ->
				if (key != updatedKey) return@OnSharedPreferenceChangeListener
				val current = sharedPreferences.all[key]
				if (prev != current) {
					prev = current
					callback()
				}
			}
		pref.registerOnSharedPreferenceChangeListener(listener)
		return Listener(pref, listener)
	}

	actual fun putDouble(key: String, value: Double) = pref.edit { putLong(key, value.toRawBits()) }

	actual fun putStringSet(key: String, value: Set<String>) =
		pref.edit { putStringSet(key, value) }

	actual fun putLong(key: String, value: Long) = pref.edit { putLong(key, value) }

	actual fun putFloat(key: String, value: Float) = pref.edit { putFloat(key, value) }

	actual fun putBoolean(key: String, value: Boolean) = pref.edit { putBoolean(key, value) }

	actual fun putInt(key: String, value: Int) = pref.edit { putInt(key, value) }

	actual fun putString(key: String, value: String) = pref.edit { putString(key, value) }

	actual operator fun minusAssign(key: String) = pref.edit { remove(key) }

	actual fun remove(key: String) = minusAssign(key)
}