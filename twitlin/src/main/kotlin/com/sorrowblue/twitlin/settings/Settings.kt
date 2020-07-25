package com.sorrowblue.twitlin.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

actual class Settings(context: Context, name: String? = null) {

	private val pref: SharedPreferences =
		name?.let { context.getSharedPreferences(it, Context.MODE_PRIVATE) }
			?: PreferenceManager.getDefaultSharedPreferences(context)

	actual fun contains(key: String): Boolean = pref.contains(key)

	actual fun getString(key: String, defValue: String?): String? = pref.getString(key, defValue)

	actual fun getStringSet(key: String, defValues: Set<String>?): Set<String>? = pref.getStringSet(key, defValues)

	actual fun putString(key: String, value: String) = pref.edit { putString(key, value) }

	actual fun putStringSet(key: String, value: Set<String>) =
		pref.edit { putStringSet(key, value) }

	actual fun remove(key: String) = pref.edit { remove(key) }
}
