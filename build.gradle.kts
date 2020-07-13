// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	id("com.github.ben-manes.versions") version "0.28.0"
}

buildscript {
	val kotlin_version by extra("1.3.72")
	repositories {
		google()
		jcenter()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:4.0.0")
		classpath(kotlin("gradle-plugin", Version.kotlin))
		// NOTE: Do not place your application dependencies here; they belong
		// in the individual module build.gradle files
	}
}

allprojects {
	repositories {
		google()
		jcenter()
		maven("https://dl.bintray.com/ekito/koin")
	}
}

tasks.register<Delete>("clean") {
	delete(rootProject.buildDir)
}
