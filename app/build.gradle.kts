/*
 * (c) 2020.
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
        val properties = gradleLocalProperties(rootDir)
        val apiKey = properties.getProperty("API_KEY")
        val apiSecret = properties.getProperty("API_SECRET")
        val accessTokenA = properties.getProperty("ACCESS_TOKEN")
        val accessTokenSecret = properties.getProperty("ACCESS_TOKEN_SECRET")
        val BearerToken = properties.getProperty("BEARER_TOKEN")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "API_SECRET", "\"$apiSecret\"")
        buildConfigField("String", "ACCESS_TOKEN", "\"$accessTokenA\"")
        buildConfigField("String", "ACCESS_TOKEN_SECRET", "\"$accessTokenSecret\"")
        buildConfigField("String", "BEARER_TOKEN", "\"$BearerToken\"")
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
        excludes.plusAssign("META-INF/**.kotlin_module")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(kotlin("stdlib", KOTLIN_VERSION))
    implementation("androidx.core:core-ktx:1.5.0-beta01")
    implementation("androidx.activity:activity-ktx:1.2.0-rc01")
    implementation("io.pixel.android:pixel:0.0.3-alpha")
    implementation("androidx.appcompat:appcompat:1.3.0-beta01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-rc01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-rc01")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.webkit:webkit:1.4.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0-beta01")
//    implementation("com.sorrowblue.twitlin:twitlin-android:0.0.1-dev-004")
    implementation(project(":twitlin"))
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
