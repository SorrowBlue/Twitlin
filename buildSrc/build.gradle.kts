/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.2")
    implementation(kotlin("gradle-plugin", "1.4.30"))
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.22.0")
}
