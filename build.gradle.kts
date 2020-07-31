// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("com.github.ben-manes.versions") version "0.29.0"
}

buildscript {
	repositories {
		google()
		jcenter()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:4.0.1")
		classpath(kotlin("gradle-plugin", Version.kotlin))
		// NOTE: Do not place your application dependencies here; they belong
		// in the individual module build.gradle files
	}
}

allprojects {
	repositories {
		google()
		jcenter()
	}
}

tasks.register<Delete>("clean") {
	delete(rootProject.buildDir)
}
