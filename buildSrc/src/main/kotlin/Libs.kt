object Libs {

	object andoridx {
		const val `core-ktx` = "androidx.core:core-ktx:1.3.0"
		const val `preference-ktx` = "androidx.preference:preference-ktx:1.1.1"
		const val `security-crypto` = "androidx.security:security-crypto:1.0.0-rc02"
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
		private const val VERSION = "1.3.2"
		const val core = "$client-core:${VERSION}"
		const val okhttp = "$client-okhttp:${VERSION}"
		const val android = "$client-android:${VERSION}"
		const val ios = "$client-ios:${VERSION}"
		const val js = "$client-js:${VERSION}"

		object serialization {
			private const val serialization = "io.ktor:ktor-client-serialization"
			const val common = "$serialization:${VERSION}"
			const val jvm = "$serialization-jvm:${VERSION}"
			const val native = "$serialization-native:${VERSION}"
			const val js = "$serialization-js:${VERSION}"
		}
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

	const val krypto = "com.soywiz.korlibs.krypto:krypto:1.11.1"

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
