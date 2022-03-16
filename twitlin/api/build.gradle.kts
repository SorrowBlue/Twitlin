@file:Suppress("UNUSED_VARIABLE")

import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication

plugins {
    kotlin("multiplatform")
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
    jvm()
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
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
        commonMain {
            kotlin.srcDirs("src/common/main/kotlin")
            dependencies {
                api(project(":twitlin:core"))
                implementation(libs.ktor.client.core)
                implementation(libs.kotlinx.serialization.core)
            }
        }
        commonTest {
            kotlin.srcDirs("src/common/test/kotlin")
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
            }
        }
        val jsMain by getting {
            kotlin.srcDirs("src/js/main/kotlin")
            dependencies {
            }
        }
        val jsTest by getting {
            kotlin.srcDirs("src/js/test/kotlin")
        }
        val jvmMain by getting {
            kotlin.srcDirs("src/jvm/main/kotlin")
            resources.srcDirs("src/jvm/main/resources")
            dependencies {
            }
        }
        val jvmTest by getting {
            kotlin.srcDirs("src/jvm/test/kotlin")
            dependencies {
            }
        }
        val androidMain by getting {
            kotlin.srcDir("src/android/main/kotlin")
            dependencies {
            }
        }
        val androidAndroidTestRelease by getting
        val androidTest by getting {
            kotlin.srcDir("src/android/test/kotlin")
            dependsOn(androidAndroidTestRelease)
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
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    sourceSets["main"].manifest.srcFile("src/android/main/AndroidManifest.xml")
}

buildkonfig {
    packageName = "com.sorrowblue.twitlin.api"
    defaultConfigs {
        com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
            .toList().filter { it.first.toString().startsWith("TWITTER_API_") }.forEach {
                buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, it.first.toString(), it.second.toString())
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
    version.set("0.44.0")
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
