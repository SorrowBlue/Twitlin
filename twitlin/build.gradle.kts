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
    signing
    kotlin("plugin.serialization") version KOTLIN_VERSION
    id("org.jetbrains.dokka") version DOKKA_VERSION
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.codingfeline.buildkonfig") version "0.7.0"
}

group = "com.sorrowblue.twitlin"
version = grgit.describe {
    longDescr = false
    isTags = true
}.let { it + if (it.matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else "" }

kotlin {
    explicitApi()
    android {
        publishLibraryVariants("release")
    }
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
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
    packageName = group.toString()
    defaultConfigs {
        gradleLocalProperties(rootDir).forEach { key, value ->
            buildConfigField(FieldSpec.Type.STRING, key.toString(), value.toString())
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

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
    }
}

afterEvaluate {
    configure<PublishingExtension> {
        publications.withType<DefaultMavenPublication>().all {
            artifact(javadocJar)
            if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                setModuleDescriptorGenerator(null)
            }
            defaultPom()
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/SorrowBlue/Twitlin")
                credentials {
                    val properties = gradleLocalProperties(rootDir)
                    username = findProperty("githubPackagesUsername") as? String
                        ?: System.getenv("githubPackagesUsername")
                    password = findProperty("githubPackagesPassword") as? String
                        ?: System.getenv("githubPackagesPassword")
                }
            }
        }
    }
}

afterEvaluate {
    signing {
        sign(publishing.publications)
    }
    publishing {
        publications.withType<DefaultMavenPublication>().all {
            artifact(javadocJar)
            println("Twitlin artifactId: $artifactId")
            if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                setModuleDescriptorGenerator(null)
            }
            defaultPom()
        }
    }
}
