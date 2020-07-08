package com.sorrowblue.twitlin

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.sorrowblue.twitlin.settings.Settings

internal class AndroidSettings(context: Context) : Settings {

	private val preference: SharedPreferences =
		PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

	override fun contains(key: String) = preference.contains(key)

	override fun getString(key: String, defValues: String) = preference.getString(key, defValues) ?: defValues
	override fun getStringOrNull(key: String) =
		if (preference.contains(key)) preference.getString(key, "") else null

	override fun getInt(key: String, defValues: Int) = preference.getInt(key, defValues)
	override fun getIntOrNull(key: String) = if (preference.contains(key)) preference.getInt(key, 0) else null

	override fun getBoolean(key: String, defValues: Boolean) = preference.getBoolean(key, defValues)
	override fun getBooleanOrNull(key: String) =
		if (preference.contains(key)) preference.getBoolean(key, false) else null

	override fun getFloat(key: String, defValues: Float): Float = preference.getFloat(key, defValues)
	override fun getFloatOrNull(key: String) =
		if (preference.contains(key)) preference.getFloat(key, 0f) else null

	override fun getLong(key: String, defValues: Long): Long = preference.getLong(key, defValues)
	override fun getLongOrNull(key: String) =
		if (preference.contains(key)) preference.getLong(key, 0) else null

	override fun getStringSet(key: String, defValues: Set<String>): Set<String> =
		preference.getStringSet(key, defValues) ?: defValues

	override fun getStringSetOrNull(key: String): MutableSet<String>? =
		if (preference.contains(key)) preference.getStringSet(key, emptySet()) else null


	override fun getDouble(key: String, defValues: Double): Double =
		Double.fromBits(preference.getLong(key, defValues.toRawBits()))

	override fun getDoubleOrNull(key: String) =
		if (preference.contains(key)) Double.fromBits(preference.getLong(key, 0.0.toRawBits())) else null


	override fun putString(key: String, value: String) = preference.edit { putString(key, value) }
	override fun minusAssign(key: String) = preference.edit { remove(key) }


	override fun putInt(key: String, value: Int) = preference.edit { putInt(key, value) }
	override fun putBoolean(key: String, value: Boolean) = preference.edit { putBoolean(key, value) }
	override fun putFloat(key: String, value: Float) = preference.edit { putFloat(key, value) }
	override fun putLong(key: String, value: Long) = preference.edit { putLong(key, value) }
	override fun putStringSet(key: String, value: Set<String>) = preference.edit { putStringSet(key, value) }
	override fun putDouble(key: String, value: Double) = preference.edit { putLong(key, value.toRawBits()) }

	override fun addListener(key: String, callback: () -> Unit): Settings.Listener {
		var prev = preference.all[key]
		val listener =
			SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, updatedKey ->
				if (key != updatedKey) return@OnSharedPreferenceChangeListener
				val current = sharedPreferences.all[key]
				if (prev != current) {
					prev = current
					callback()
				}
			}
		preference.registerOnSharedPreferenceChangeListener(listener)
		return Listener(preference, listener)
	}

	class Listener(
		private val pref: SharedPreferences,
		private val listener: SharedPreferences.OnSharedPreferenceChangeListener
	) :
		Settings.Listener {
		override fun remove() {
			pref.unregisterOnSharedPreferenceChangeListener(listener)
		}
	}
}
