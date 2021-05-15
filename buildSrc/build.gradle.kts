/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-alpha15")
    implementation(kotlin("gradle-plugin", "1.5.0"))
}
