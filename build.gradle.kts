/*
 * (c) 2020-2021 SorrowBlue.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.benmanes.versions)
    alias(libs.plugins.nexus.publish)
    alias(libs.plugins.ajoberstar.grgit)
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
    }
}

group = "com.sorrowblue.twitlin"

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
fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}
tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates").configure {
    this.rejectVersionIf {
        candidate.version.isNonStable() && !currentVersion.isNonStable()
    }
}

fun String.toVersion() = this + if (matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""
