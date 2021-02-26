/*
 * (c) 2020-2021 SorrowBlue.
 */

@file:Suppress("UNUSED_VARIABLE")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.sorrowblue.gradle.mavenCentralPublish

plugins {
    `kotlin-multiplatform`
    ComAndroidPluginGroup(this).library
    `kotlin-parcelize`
    kotlin("plugin.serialization") version KOTLIN_VERSION
    id("org.jetbrains.dokka") version DOKKA_VERSION
    id("com.sorrowblue.gradle.github-packages-publish") version "1.0.0"
    id("com.sorrowblue.gradle.maven-central-publish") version "1.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("org.ajoberstar.grgit") version "4.1.0"
}

group = "com.sorrowblue.twitlin"
version = "1.0.0-001-SNAPSHOT"

kotlin {
    explicitApi()
    android {
        publishLibraryVariants("release")
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        nodejs()
        browser {
            webpackTask {
                output.libraryTarget = "umd"
            }
            testTask {
                useMocha()
            }
        }
    }
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
        }
        commonMain {
            kotlin.srcDirs("src/common/main/kotlin")
            dependencies {
                api(Libs.kotlinx.datetime)
                implementation(Libs.kotlinx.serialization)
                implementation(Libs.kotlinx.`serialization-properties`)
                implementation(Libs.`ktor-client`.core)
                implementation(Libs.`ktor-client`.serialization)
                implementation(kotlin("reflect", KOTLIN_VERSION))
                implementation(Libs.napier.common)
            }
        }
        commonTest {
            kotlin.srcDirs("src/common/test/kotlin")
            resources.srcDirs("src/common/test/resources")
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            kotlin.srcDirs("src/js/main/kotlin")
            dependencies {
                implementation(Libs.`ktor-client`.js)
                implementation(npm("jssha", "3.2.0"))
                implementation(npm("@sinonjs/text-encoding", "0.7.1"))
            }
        }
        val jsTest by getting {
            kotlin.srcDirs("src/js/test/kotlin")
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libs.`ktor-client`.android)
                implementation(Libs.jsoup)
                implementation(Libs.andoridx.`security-crypto`)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jvmMain by getting {
            kotlin.srcDirs("src/jvm/main/kotlin")
            dependencies {
                implementation(Libs.`ktor-client`.okhttp)

                implementation(Libs.jsoup)
            }
        }
        val jvmTest by getting {
            kotlin.srcDirs("src/jvm/test/kotlin")
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    sourceSets {
        val main by getting {
            manifest.srcFile("src/android/main/AndroidManifest.xml")
            java.srcDirs("src/android/main/kotlin")
        }
        val test by getting {
            java.srcDirs("src/android/test/kotlin")
        }
    }
}

tasks.dokkaHtml.configure {
    moduleName.set("docs")
    outputDirectory.set(rootProject.projectDir.resolve("docs/"))
    dokkaSourceSets {
        named("commonMain") {
            displayName.set("common")
            platform.set(org.jetbrains.dokka.Platform.common)
        }
        named("jvmMain") {
            platform.set(org.jetbrains.dokka.Platform.jvm)
        }
        named("jsMain") {
            platform.set(org.jetbrains.dokka.Platform.js)
        }
        named("androidMain") {
            platform.set(org.jetbrains.dokka.Platform.valueOf("android"))
        }
    }
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
    }
}

ext {
    val tags = grgit.tag.list().map(org.ajoberstar.grgit.Tag::getName)
    val versionStr = if (tags.any { it.matches("v\\d.*".toRegex()).not() }) {
        grgit.describe {
            longDescr = false
            isTags = true
            match = listOf("v[0-9]*")
        }
    } else {
        grgit.head().abbreviatedId
    }
    version = versionStr + (if (grgit.status().isClean) "" else "+dirty")
    println("version: $version")
}

tasks.register("showVersion") {
    doLast {
        println(extra["version"])
    }
}

githubPackages {
    username = findProperty("github.username")?.toString() ?: System.getenv("GITHUB_USERNAME")
    password = findProperty("github.token")?.toString() ?: System.getenv("GITHUB_TOKEN")
    repo = "$username/Twitlin"
}

mavenCentralPublish {
    val p = gradleLocalProperties(rootDir)
    version = version.toString()
    signingKeyId = p.getOrElse("signing.keyId") { System.getenv("SIGNING_KEY_ID") }.toString()
    signingPassword =
        p.getOrElse("signing.password") { System.getenv("SIGNING_PASSWORD") }.toString()
    signingSecretKeyRingFile =
        p.getOrElse("signing.secretKeyRingFile") { System.getenv("SIGNING_SECRET_KEY_RING_FILE") }
            .toString()
    username = p.getOrElse("ossrhUsername") { System.getenv("OSSRH_USERNAME") }.toString()
    password = p.getOrElse("ossrhPassword") { System.getenv("OSSRH_PASSWORD") }.toString()
    stagingProfileId =
        p.getOrElse("sonatypeStagingProfileId") { System.getenv("SONATYPE_STAGING_PROFILE_ID") }
            .toString()
}
