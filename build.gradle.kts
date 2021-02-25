/*
 * (c) 2020-2021 SorrowBlue.
 */
plugins {
    id("com.github.ben-manes.versions").version("0.36.0")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath(kotlin("gradle-plugin", KOTLIN_VERSION))
        classpath("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.22.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        jcenter()
        maven(url ="http://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
}
