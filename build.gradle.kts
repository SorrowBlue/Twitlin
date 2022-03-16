/*
 * (c) 2020-2021 SorrowBlue.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.benmanes.versions)
    alias(libs.plugins.nexus.publish)
    alias(libs.plugins.ajoberstar.grgit)
    kotlin("multiplatform") version "1.6.20-RC" apply false
    id("com.android.application") version "7.0.4" apply false
    id("com.android.library") version "7.0.4" apply false
    kotlin("plugin.serialization") version "1.6.20-RC"
    id("org.jetbrains.dokka") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "com.sorrowblue.twitlin"

version = grgit.describe {
    longDescr = false
    tags = true
}.toVersion().also { logger.lifecycle("version: $it") }

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
