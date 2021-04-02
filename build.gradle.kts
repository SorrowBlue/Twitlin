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
        gradlePluginPortal()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath(kotlin("gradle-plugin", KOTLIN_VERSION))
        classpath("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.30.0")
    }
}

group = "com.sorrowblue.twitlin"

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        jcenter()
        maven(url ="https://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
}

ext {
    val versionStr = grgit.describe {
        longDescr = false
        isTags = true
    }
    version = versionStr + if (versionStr.matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""
    println("version: $version")
}

nexusPublishing {
    val p = gradleLocalProperties(rootDir)
    repositories {
        sonatype {
            stagingProfileId.set(p.getOrElse("sonatypeStagingProfileId") { System.getenv("sonatypeStagingProfileId") }.toString())
            username.set(p.getOrElse("ossrhUsername") { System.getenv("OSSRH_USERNAME") }.toString())
            password.set(p.getOrElse("ossrhPassword") { System.getenv("OSSRH_PASSWORD") }.toString())
        }
    }
}
