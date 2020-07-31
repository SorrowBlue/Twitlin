import org.jetbrains.dokka.gradle.DokkaTask

plugins {
	`kotlin-multiplatform`
	kotlin("plugin.serialization") version Version.kotlin
	`maven-publish`
	id("org.jetbrains.dokka") version "0.10.1"
}

repositories {
	mavenCentral()
	jcenter()
}

group = "com.sorrowblue.twitlin"
version = "0.0.1"

apply(from = "android.gradle")
apply(from = "publish.gradle")

kotlin {

	android("android") {
		publishLibraryVariants("release")
	}

	jvm()

	iosX64("ios") {
		binaries {
			framework()
		}
	}
	sourceSets {

		all {
			languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
		}

		commonMain {
			dependencies {
				implementation(kotlin("stdlib-common"))
				implementation(Libs.kotlinx.serialization.`runtime-common`)
				implementation(Libs.`ktor-client`.core)
				implementation(Libs.`ktor-client`.serialization.common)
				implementation(Libs.napier.common)
				api(Libs.klock.common)
				implementation(Libs.koin.core)
				implementation(Libs.dagger.`hilt-android`)
			}
		}

		commonTest {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}

		val androidMain by getting {
			dependencies {
				implementation(kotlin("stdlib"))
				implementation(Libs.kotlinx.serialization.runtime)
				implementation("org.jsoup:jsoup:1.13.1")
				implementation(Libs.`ktor-client`.android)
				implementation(Libs.`ktor-client`.serialization.jvm)
				implementation(Libs.napier.android)
				implementation(Libs.koin.android)
				api(Libs.klock.android)
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
				implementation(kotlin("stdlib"))
				implementation(Libs.kotlinx.serialization.runtime)
				implementation("org.jsoup:jsoup:1.13.1")
				implementation(Libs.napier.jvm)
				implementation(Libs.`ktor-client`.okhttp)
				implementation(Libs.`ktor-client`.serialization.jvm)
				api(Libs.klock.jvm)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(kotlin("test-junit"))
			}
		}

		val iosMain by getting {
			dependencies {
				implementation(Libs.kotlinx.serialization.`runtime-native`)
				implementation(Libs.`ktor-client`.ios)
				implementation(Libs.napier.ios)
				implementation(Libs.`ktor-client`.serialization.native)
				implementation(Libs.krypto)
				api(Libs.klock.iosx64)
			}
		}

		val iosTest by getting {
		}

	}
}

tasks {
	val dokka by getting(DokkaTask::class) {
		outputFormat = "html"
		outputDirectory = "${rootProject.projectDir}/docs"

		multiplatform {
			val customName by creating { // The same name as in Kotlin Multiplatform plugin, so the sources are fetched automatically
//				includes = listOf("packages.md", "extra.md")
//				samples = listOf("samples/basic.kt", "samples/advanced.kt")
			}

			register("jvm") { // Different name, so source roots must be passed explicitly
				targets = listOf("JVM")
				platform = "jvm"
				sourceRoot {
					path = kotlin.sourceSets.getByName("jvmMain").kotlin.srcDirs.first().toString()
				}
				sourceRoot {
					path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
				}
			}
			register("android") { // Different name, so source roots must be passed explicitly
				targets = listOf("Android")
				platform = "android"
				sourceRoot {
					path = kotlin.sourceSets.getByName("androidMain").kotlin.srcDirs.first().toString()
				}
				sourceRoot {
					path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
				}
			}
		}
	}
}

configurations.create("compileClasspath")
