/*
 * (c) 2020-2021 SorrowBlue.
 */

@file:Suppress("UNUSED_VARIABLE")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication

plugins {
    `kotlin-multiplatform`
    ComAndroidPluginGroup(this).library
    `kotlin-parcelize`
    `maven-publish`
    kotlin("plugin.serialization") version KOTLIN_VERSION
    id("org.jetbrains.dokka") version DOKKA_VERSION
    id("com.sorrowblue.gradle.github-packages-publish") version "1.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.codingfeline.buildkonfig") version "0.7.0"
}

group = "com.sorrowblue.twitlin"

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

buildkonfig {
    packageName = "com.sorrowblue.twitlin"
    defaultConfigs {
        gradleLocalProperties(rootDir).forEach { t, u ->
            buildConfigField(FieldSpec.Type.STRING, t.toString().replace('.', '_'), u.toString())
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

githubPackages {
    username = findProperty("github.username")?.toString() ?: System.getenv("GITHUB_USERNAME")
    password = findProperty("github.token")?.toString() ?: System.getenv("GITHUB_TOKEN")
    repo = "$username/Twitlin"
}

ext {
    val versionStr = grgit.describe {
        longDescr = false
        isTags = true
    }
    version = versionStr + if (versionStr.matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""
    println("version: $version")
}

afterEvaluate {
    publishing {
        publications.withType<DefaultMavenPublication>().all {
            println("Twitlin artifactId: $artifactId")
            if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                setModuleDescriptorGenerator(null)
            }
            pom {
                name.set(artifactId)
                description.set("Twitlin for Twitter API")
                url.set("https://github.com/SorrowBlue/Twitlin")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("sorrowblue_sb")
                        name.set("Sorrow Blue")
                        email.set("sorrowblue.sb@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/SorrowBlue/Twitlin.git")
                    developerConnection.set("scm:git:ssh://github.com/SorrowBlue/Twitlin.git")
                    url.set("https://github.com/SorrowBlue/Twitlin/tree/main")
                }
            }
        }
    }
}
