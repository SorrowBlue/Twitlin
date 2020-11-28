@file:Suppress("UNUSED_VARIABLE")

import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication

plugins {
    `kotlin-multiplatform`
    ComAndroidPluginGroup(this).library
    `kotlin-parcelize`
    `kotlin-kapt`
    kotlin("plugin.serialization") version KOTLIN_VERSION
    id("org.jetbrains.dokka") version "1.4.10.2"
    `maven-publish`
}

group = "com.sorrowblue.twitlin"
version = "0.0.1-dev-004"

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
        }
        commonMain {
            dependencies {
                api(Libs.kotlinx.datetime)
                implementation(Libs.kotlinx.serialization)
                implementation(Libs.`ktor-client`.core)
                implementation(Libs.`ktor-client`.serialization)
                implementation(kotlin("reflect", KOTLIN_VERSION))
                api(Libs.klock)

                implementation(Libs.napier.common)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(Libs.`ktor-client`.js)

                implementation("org.webjars.npm:crypto-js:4.0.0")
            }
        }
        val jsTest by getting {
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
            dependencies {
                implementation(Libs.`ktor-client`.okhttp)

                implementation(Libs.jsoup)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(rootProject.projectDir.resolve("docs/javadoc"))
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
    val sourcesJar = task("sourcesJar", Jar::class) {
        @Suppress("UnstableApiUsage")
        archiveClassifier.apply {
            convention("sources")
            set("sources")
        }
        if (project.name == "twitlin") {
            from(kotlin.sourceSets.commonMain.get().kotlin)
        } else {
            from(sourceSets.getAt("main").allSource)
        }
    }
    publishing {
        val projectName = project.name
        publications.all<DefaultMavenPublication> {
            artifactId = when (name) {
                "kotlinMultiplatform" -> {
                    artifact(sourcesJar)
                    "$projectName-ios"
                }
                "metadata" -> projectName
                "androidRelease" -> "$projectName-android"
                else -> "$projectName-$name"
            }
            if (it.name.contains("ios").not() && it.name != "kotlinMultiPlatform") {
                setModuleDescriptorGenerator(null)
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/SorrowBlue/twitlin")
                credentials {
                    username =
                        project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                    password =
                        project.findProperty("gpr.token")?.toString() ?: System.getenv("TOKEN")
                }
            }
        }
    }
}

fun <T> PublicationContainer.all(action: T.(Publication) -> Unit) = all {
    @Suppress("UNCHECKED_CAST")
    action(this as T, this)
}
