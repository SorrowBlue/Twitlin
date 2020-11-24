// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("com.github.ben-manes.versions") version "0.36.0"
}

buildscript {
	repositories {
		gradlePluginPortal()
		jcenter()
		google()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:4.0.2")
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
		maven(url = "https://kotlin.bintray.com/kotlinx/")
	}
}
