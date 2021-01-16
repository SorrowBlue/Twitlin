/*
 * (c) 2020.
 */

@file:Suppress("ClassName")

object Libs {

    object andoridx {
        const val `core-ktx` = "androidx.core:core-ktx:1.3.0"
        const val `preference-ktx` = "androidx.preference:preference-ktx:1.1.1"
        const val `security-crypto` = "androidx.security:security-crypto:1.1.0-alpha03"
    }

    object kotlinx {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1"
        const val `serialization-json` = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
        const val `coroutines-test` = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
        const val `serialization-properties` =
            "org.jetbrains.kotlinx:kotlinx-serialization-properties:1.0.1"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.1.1"
    }

    object `ktor-client` {
        private const val VERSION = "1.5.0"
        const val core = "io.ktor:ktor-client-core:${VERSION}"
        const val okhttp = "io.ktor:ktor-client-okhttp:${VERSION}"
        const val android = "io.ktor:ktor-client-android:${VERSION}"
        const val ios = "io.ktor:ktor-client-ios:${VERSION}"
        const val js = "io.ktor:ktor-client-js:${VERSION}"

        const val serialization = "io.ktor:ktor-client-serialization:${VERSION}"
    }

    const val jsoup = "org.jsoup:jsoup:1.13.1"

    object napier {
        private const val version = "1.4.1"
        const val common = "com.github.aakira:napier:$version"
        const val android = "com.github.aakira:napier-android:$version"
        const val ios = "com.github.aakira:napier-ios:$version"
        const val js = "com.github.aakira:napier-js:$version"
        const val jvm = "com.github.aakira:napier-jvm:$version"
    }
}
