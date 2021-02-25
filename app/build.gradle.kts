/*
 * (c) 2020-2021 SorrowBlue.
 */

@file:Suppress("UNUSED_VARIABLE")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    ComAndroidPluginGroup(this).application
    `kotlin-android`
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.sorrowblue.twitlin.androidsample"
        minSdkVersion(26)
        targetSdkVersion(30)
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
    implementation(kotlin("stdlib-jdk8", KOTLIN_VERSION))
    implementation("androidx.core:core-ktx:1.5.0-beta01")
    implementation("androidx.activity:activity-ktx:1.3.0-alpha02")
    implementation("io.pixel.android:pixel:0.0.3-alpha")
    implementation("androidx.appcompat:appcompat:1.3.0-beta01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.webkit:webkit:1.4.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0-beta01")
    implementation(project(":twitlin"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
