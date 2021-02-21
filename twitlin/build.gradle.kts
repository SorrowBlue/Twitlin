/*
 * (c) 2020-2021 SorrowBlue.
 */

@file:Suppress("UNUSED_VARIABLE")

plugins {
    `kotlin-multiplatform`
    ComAndroidPluginGroup(this).library
    `kotlin-parcelize`
    `kotlin-kapt`
    kotlin("plugin.serialization") version KOTLIN_VERSION
    id("org.jetbrains.dokka") version "1.4.20"
    `maven-publish`
    id("signing")
    id("io.codearte.nexus-staging")
}

group = "com.sorrowblue.twitlin"
version = "1.0.0-001-SNAPSHOT"

extra["PUBLISH_GROUP_ID"] = group.toString()
extra["PUBLISH_VERSION"] = version.toString()

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

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
                implementation("org.webjars.npm:crypto-js:4.0.0")
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

afterEvaluate {
    apply<MavenCentralRepository>()
    apply<GithubPackagesRepository>()
}
