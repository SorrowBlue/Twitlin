@file:Suppress("UNUSED_VARIABLE")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication

plugins {
    kotlin("multiplatform")
    id("io.kotest.multiplatform") version "5.3.0"
    id("com.android.library")
    id("kotlin-parcelize")
    `maven-publish`
    signing
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.ajoberstar.grgit")
    id("com.codingfeline.buildkonfig") version "0.11.0"
}

group = "com.sorrowblue.twitlin"
version = grgit.describe {
    longDescr = false
    isTags = true
}?.toVersion() ?: "0.0.1-SNAPSHOT"

fun String.toVersion() = this + if (matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""

kotlin {
    explicitApi()
    android {
        publishLibraryVariants("release")
    }
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
            systemProperties = System.getProperties().toList().associate { it.first.toString() to it.second }
        }
    }
    js(IR) {
        browser()
        nodejs()
    }
    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
        commonMain {
            kotlin.srcDirs("src/common/main/kotlin")
            dependencies {
                api(project(":library:core"))
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.bundles.kotlinx.serialization)
            }
        }
        commonTest {
            kotlin.srcDirs("src/common/test/kotlin")
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.framework.datatest)
                implementation(libs.kotest.property)
            }
        }
        val jsMain by getting {
            kotlin.srcDirs("src/js/main/kotlin")
        }
        val jsTest by getting {
            kotlin.srcDirs("src/js/test/kotlin")
        }
        val jvmMain by getting {
            kotlin.srcDirs("src/jvm/main/kotlin")
        }
        val jvmTest by getting {
            kotlin.srcDirs("src/jvm/test/kotlin")
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation(libs.selenium)
            }
        }
        val androidMain by getting {
            kotlin.srcDir("src/android/main/kotlin")
        }
        val androidAndroidTestRelease by getting
        val androidTest by getting {
            kotlin.srcDir("src/android/test/kotlin")
            dependsOn(androidAndroidTestRelease)
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation("junit:junit:4.13.2")
                implementation("androidx.test.ext:junit:1.1.3")
                implementation("androidx.test.espresso:espresso-core:3.4.0")
                implementation("org.robolectric:robolectric:4.8")
            }
        }
    }
}

android {
    compileSdk = libs.versions.android.targetSdkVersion.get().toInt()
    buildToolsVersion = libs.versions.android.buildToolsVersion.get()

    defaultConfig {
        minSdk = libs.versions.android.minSdkVersion.get().toInt()
        targetSdk = libs.versions.android.targetSdkVersion.get().toInt()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    testOptions.unitTests.isIncludeAndroidResources = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets["main"].manifest.srcFile("src/android/main/AndroidManifest.xml")
}

buildkonfig {
    packageName = "com.sorrowblue.twitlin.api.v2"
    defaultConfigs {
        gradleLocalProperties(rootDir)
            .filter { it.key.toString().startsWith("BuildKonfig.") }
            .forEach {
                buildConfigField(
                    FieldSpec.Type.STRING,
                    it.key.toString().replaceFirst("BuildKonfig.", ""),
                    it.value.toString()
                )
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
    version.set("0.45.2")
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        filter {
            exclude("**/BuildKonfig.kt")
        }
    }
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
