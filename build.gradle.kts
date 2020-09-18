// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("com.github.ben-manes.versions") version "0.30.0"
}

buildscript {
	repositories {
		gradlePluginPortal()
		jcenter()
		google()
		maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
	}
	dependencies {
		classpath("com.android.tools.build:gradle:4.0.1")
		classpath(kotlin("gradle-plugin", KOTLIN_VERSION))
		// NOTE: Do not place your application dependencies here; they belong
		// in the individual module build.gradle files
	}
}

allprojects {
	repositories {
		gradlePluginPortal()
		google()
		jcenter()
		mavenCentral()
		maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
		maven(url = "https://kotlin.bintray.com/kotlinx/")
	}
}
