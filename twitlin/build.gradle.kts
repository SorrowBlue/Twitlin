@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
	`kotlin-multiplatform`
	ComAndroidPluginGroup(this).library
	`kotlin-android-extensions`
	`kotlin-kapt`
	kotlin("plugin.serialization") version KOTLIN_VERSION
	id("org.jetbrains.dokka") version "0.10.1"
	`maven-publish`
}

group = "com.sorrowblue.twitlin"
version = "0.0.1-dev-001"

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
				implementation(Libs.kotlinx.serialization.runtime)
				implementation(Libs.`ktor-client`.core)
				implementation(Libs.`ktor-client`.serialization.common)
				implementation(Libs.napier.common)
				api(Libs.klock)
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
				implementation(Libs.`ktor-client`.serialization.js)
				implementation(Libs.napier.js)
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
				implementation(Libs.`ktor-client`.serialization.jvm)
				implementation(Libs.napier.android)

				implementation(Libs.jsoup)
				implementation(Libs.andoridx.`security-crypto`)
				implementation(Libs.andoridx.`preference-ktx`)
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
				implementation(Libs.`ktor-client`.serialization.jvm)
				implementation(Libs.napier.jvm)

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
	buildToolsVersion("30.0.1")

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

tasks.named<DokkaTask>("dokka") {
	outputFormat = "html"
	outputDirectory = "${rootProject.projectDir}/docs"

	multiplatform {
		create("jvm") {
			targets = listOf("JVM")
			platform = "jvm"
			skipEmptyPackages = true
		}
		create("android") {
			targets = listOf("Android")
			platform = "android"
			skipEmptyPackages = true
		}
		create("js") {
			targets = listOf("js")
			platform = "js"
			skipEmptyPackages = true
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
		publications.all<MavenPublication> {
			artifactId = projectName
			groupId = "com.sorrowblue.twitlin"
			when (name) {
				"kotlinMultiplatform" -> {
					artifactId = projectName
					artifact(sourcesJar)
				}
				"metadata" -> artifactId = "$projectName-common"
				"androidRelease" -> artifactId = "$projectName-android"
				else -> {
					artifactId = "$projectName-$name"
				}
			}
		}
		repositories {
			maven {
				name = "GitHubPackages"
				url = uri("https://maven.pkg.github.com/SorrowBlue/twitlin")
				credentials {
					username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
					password = project.findProperty("gpr.token")?.toString() ?: System.getenv("TOKEN")
				}
			}
		}
	}
}

fun <T> PublicationContainer.all(action: T.(Publication) -> Unit) = all {
	@Suppress("UNCHECKED_CAST")
	action(this as T, this)
}
