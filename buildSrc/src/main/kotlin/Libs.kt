object Libs {

	object andoridx {
		const val `core-ktx` = "androidx.core:core-ktx:1.3.0"
		const val `preference-ktx` = "androidx.preference:preference-ktx:1.1.1"
		const val `startup-runtime` = "androidx.startup:startup-runtime:1.0.0-alpha01"
	}

	object kotlinx {
		object serialization {
			private const val serialization_version = "0.20.0"
			const val runtime =
				"org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version"
			const val `runtime-common` =
				"org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version"
			const val `runtime-js` =
				"org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serialization_version"
			const val `runtime-native` =
				"org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version"
		}
	}

	object `ktor-client` {
		private const val client = "io.ktor:ktor-client"
		private const val `ktor-client_version` = "1.3.2"
		const val core = "$client-core:${`ktor-client_version`}"
		const val okhttp = "$client-okhttp:${`ktor-client_version`}"
		const val android = "$client-android:${`ktor-client_version`}"
		const val ios = "$client-ios:${`ktor-client_version`}"
		const val js = "$client-js:${`ktor-client_version`}"

		object serialization {
			private const val serialization = "io.ktor:ktor-client-serialization"
			const val common = "$serialization:${`ktor-client_version`}"
			const val jvm = "$serialization-jvm:${`ktor-client_version`}"
			const val native = "$serialization-native:${`ktor-client_version`}"
			const val js = "$serialization-js:${`ktor-client_version`}"
		}
	}

	object dagger {
		private const val VERSION = "2.28.3-alpha"
		const val `hilt-android` = "com.google.dagger:hilt-android:$VERSION"
		const val `hilt-android-compiler` = "com.google.dagger:hilt-android-compiler:$VERSION"
		const val `hilt-android-gradle-plugin` = "com.google.dagger:hilt-android-gradle-plugin:$VERSION"
	}

	object klock {
		private const val group = "com.soywiz.korlibs.klock"
		private const val klock_version = "1.11.13"
		const val common = "$group:klock:$klock_version"
		const val jvm = "$group:klock-jvm:$klock_version"
		const val android = "$group:klock-android:$klock_version"
		const val iosx64 = "$group:klock-iosx64:$klock_version"
		const val js = "$group:klock-js:$klock_version"
	}

	object koin {
		const val koin_version = "3.0.0-alpha-2"
		const val core = "org.koin:koin-core:$koin_version"
		const val android = "org.koin:koin-android:$koin_version"
	}

	const val krypto = "com.soywiz.korlibs.krypto:krypto:1.11.1"
	object settings {
		private const val version = "0.6"
		private const val settings = "com.russhwolf:multiplatform-settings"
		const val common = "$settings:$version"
		const val `no-arg` = "$settings-no-arg:$version"
		const val android = "$settings-android:$version"
		const val jvm = "$settings-jvm:$version"
		const val js = "$settings-js:$version"
		const val ios = "$settings-ios:$version"
	}

	object napier {
		private const val version = "1.3.0"
		private const val napier = "com.github.aakira:napier"
		const val common = "$napier:$version"
		const val android = "$napier-android:$version"
		const val ios = "$napier-ios:$version"
		const val js = "$napier-js:$version"
		const val jvm = "$napier-jvm:$version"
	}
}
