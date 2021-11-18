/*
 * (c) 2020-2021 SorrowBlue.
 */

@file:Suppress("UNUSED_VARIABLE")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(libs.versions.android.targetSdkVersion.get().toInt())
    buildToolsVersion = libs.versions.android.buildToolsVersion.get()
    defaultConfig {
        applicationId = "com.sorrowblue.twitlin.androidsample"
        minSdkVersion(libs.versions.android.minSdkVersion.get().toInt())
        targetSdkVersion(libs.versions.android.targetSdkVersion.get().toInt())
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        gradleLocalProperties(rootDir).forEach { any, any2 ->
            if (any.toString().indexOf('.') == -1) {
                buildConfigField("String", "$any", "\"$any2\"")
            }
        }
    }
    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packagingOptions {
        exclude("META-INF/**.kotlin_module")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(":twitlin"))
    implementation(kotlin("stdlib-jdk8", "1.6.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.webkit:webkit:1.4.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("io.coil-kt:coil:1.4.0")
    implementation("io.insert-koin:koin-android:3.1.3")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
