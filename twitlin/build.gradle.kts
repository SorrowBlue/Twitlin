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
}?.toVersion() ?: "0.0.1-SNAPSHOT"

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
    js(IR) {
        nodejs()
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
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
                implementation(Libs.`kotlin-logging`)
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
            kotlin.srcDir("src/android/main/kotlin")
            dependencies {
                implementation(Libs.`ktor-client`.android)
                implementation(Libs.jsoup)
                implementation(Libs.andoridx.`security-crypto`)
                implementation(Libs.`slf4j-android`)
            }
        }
        val androidTest by getting {
            kotlin.srcDir("src/android/test/kotlin")
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jvmMain by getting {
            kotlin.srcDirs("src/jvm/main/kotlin")
            resources.srcDirs("src/jvm/main/resources")
            dependencies {
                implementation(Libs.`ktor-client`.okhttp)
                implementation(Libs.jsoup)
                implementation(Libs.`slf4j-simple`)
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
    sourceSets["main"].manifest.srcFile("src/android/main/AndroidManifest.xml")
}

buildkonfig {
    packageName = group.toString()
    defaultConfigs {
        gradleLocalProperties(rootDir).toList().filter { it.first.toString().startsWith("QIITA_API_") }.forEach {
            buildConfigField(FieldSpec.Type.STRING, it.first.toString(), it.second.toString())
        }
    }
}

tasks.dokkaHtml.configure {
    moduleName.set("docs")
    outputDirectory.set(rootProject.projectDir.resolve("docs/dokka/"))
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

tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask> {
    reportsOutputDirectory.set(
        project.layout.buildDirectory.dir("$rootDir/docs/reports")
    )
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url =
                    uri("https://maven.pkg.github.com/${findProperty("githubPackagesUsername")}/Twitlin")
                credentials {
                    username = findProperty("githubPackagesUsername") as? String
                    password = findProperty("githubPackagesPassword") as? String
                }
            }
        }
        publications.withType<DefaultMavenPublication>().all {
            logger.lifecycle("artifactId: ${this.artifactId}")
            artifact(javadocJar)
            if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                setModuleDescriptorGenerator(null)
            }
            defaultPom()
        }
    }
    signing {
        if (!hasProperty("signing.secretKeyRingFile")) {
            val signingKeyId: String? by project
            val signingKey: String? by project
            val signingPassword: String? by project
            useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        }
        sign(publishing.publications)
    }
}
