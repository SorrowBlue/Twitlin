import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
        jcenter().mavenContent {
            includeGroup("com.github.aakira")
        }
    }
}

version = grgit.describe {
    longDescr = false
    isTags = true
}.let { it + if (it.matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else "" }

with(gradleLocalProperties(rootDir)) {
    fun loadProperty(key: String) {
        extra[key] = getProperty(key) ?: System.getenv(key)
    }
    loadProperty("signing.keyId")
    loadProperty("signing.password")
    loadProperty("signing.secretKeyRingFile")
    loadProperty("sonatypeUsername")
    loadProperty("sonatypePassword")
    loadProperty("sonatypeStagingProfileId")
}

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(extra["sonatypeStagingProfileId"] as? String)
        }
    }
}
