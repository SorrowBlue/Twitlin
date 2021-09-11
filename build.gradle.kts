/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    id("com.github.ben-manes.versions").version("0.39.0")
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("org.ajoberstar.grgit") version "4.1.0"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", "1.5.30"))
        classpath("com.android.tools.build:gradle:4.2.2")
    }
}

group = "com.sorrowblue.twitlin"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

version = grgit.describe {
    longDescr = false
    isTags = true
}?.toVersion().also { logger.lifecycle("version: $it") } ?: "0.0.1-SNAPSHOT"

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(findProperty("sonatypeStagingProfileId") as? String)
        }
    }
}

fun String.toVersion() = this + if (matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""
