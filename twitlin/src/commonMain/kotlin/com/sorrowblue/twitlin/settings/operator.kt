import com.sorrowblue.twitlin.settings.Settings

@Suppress("IMPLICIT_CAST_TO_ANY")
inline operator fun <reified T : Any> Settings.get(key: String): T? =
	when (T::class) {
		Int::class -> getIntOrNull(key)
		String::class -> getStringOrNull(key)
		Float::class -> getFloatOrNull(key)
		Double::class -> getDoubleOrNull(key)
		Boolean::class -> getBooleanOrNull(key)
		Set::class -> getStringSetOrNull(key)
		else -> throw IllegalArgumentException("type")
	} as? T

inline operator fun <reified T : Any> Settings.set(key: String, value: T?) {
	if (value == null) {
		this -= key
	} else when (value) {
		is Int -> putInt(key, value)
		is String -> putString(key, value)
		is Float -> putFloat(key, value)
		is Double -> putDouble(key, value)
		is Boolean -> putBoolean(key, value)
		is Set<*> -> @Suppress("UNCHECKED_CAST")
		if (value.all { it is String }) putStringSet(key, value as Set<String>)
		else -> throw IllegalArgumentException("type")
	}
}
