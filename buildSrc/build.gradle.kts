import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * (c) 2020-2021 SorrowBlue.
 */

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.4.30"
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
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
