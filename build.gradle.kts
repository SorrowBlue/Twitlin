/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    id("com.github.ben-manes.versions").version("0.38.0")
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
    id("org.ajoberstar.grgit") version "4.1.0"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath(kotlin("gradle-plugin", KOTLIN_VERSION))
    }
}

group = "com.sorrowblue.twitlin"

allprojects {
    repositories {
        google()
        mavenCentral()
        // TODO Remove the AndroidGradlePlugin when it no longer depends on jcenter.
        //  Correctly trove4j and aakira.
        jcenter().mavenContent {
            includeGroup("org.jetbrains.trove4j")
            includeGroup("com.github.aakira")
        }
        maven("https://dl.bintray.com/kotlin/kotlinx/")
    }
}

version = grgit.describe {
    longDescr = false
    isTags = true
}?.let { it + if (it.matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else "" }.also {
    logger.lifecycle("version: $it")
} ?: "0.0.1-SNAPSHOT"

logger.lifecycle("sonatypeUsername: ${hasProperty("sonatypeUsername")}")
logger.lifecycle("sonatypePassword: ${hasProperty("sonatypePassword")}")
logger.lifecycle("sonatypeStagingProfileId: ${hasProperty("sonatypeStagingProfileId")}")
logger.lifecycle("signing.keyId: ${hasProperty("signing.keyId")}")
logger.lifecycle("signing.password: ${hasProperty("signing.password")}")
logger.lifecycle("signing.secretKeyRingFile: ${hasProperty("signing.secretKeyRingFile")}")
logger.lifecycle("githubPackagesUsername: ${hasProperty("githubPackagesUsername")}")
logger.lifecycle("githubPackagesPassword: ${hasProperty("githubPackagesPassword")}")

if (listOf(
        "sonatypeUsername",
        "sonatypePassword",
        "sonatypeStagingProfileId",
        "signing.keyId",
        "signing.password",
        "signing.secretKeyRingFile"
    ).all(::hasProperty)
) {
    nexusPublishing {
        repositories {
            sonatype {
                stagingProfileId.set(findProperty("sonatypeStagingProfileId") as? String)
            }
        }
    }
}
