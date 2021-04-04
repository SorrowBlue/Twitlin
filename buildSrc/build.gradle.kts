/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    // TODO Remove the AndroidGradlePlugin when it no longer depends on jcenter. Correctly trove4j.
    jcenter().mavenContent {
        includeGroup("org.jetbrains.trove4j")
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.1.3")
    implementation(kotlin("gradle-plugin", "1.4.32"))
}
