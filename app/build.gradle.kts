@file:Suppress("UNUSED_VARIABLE")

plugins {
	ComAndroidPluginGroup(this).application
	`kotlin-android`
	`kotlin-android-extensions`
}

android {
	compileSdkVersion(30)
	buildToolsVersion = "30.0.1"
	defaultConfig {
		applicationId = "com.sorrowblue.twitlin.androidsample"
		minSdkVersion(26)
		targetSdkVersion(30)
		versionCode = 1
		versionName = "1.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
	implementation("androidx.core:core-ktx:1.5.0-alpha02")
	implementation("androidx.activity:activity-ktx:1.2.0-alpha08")
	implementation("androidx.appcompat:appcompat:1.3.0-alpha02")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha07")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha07")
	implementation("androidx.constraintlayout:constraintlayout:2.0.1")
	implementation("androidx.webkit:webkit:1.3.0")
	implementation(project(":twitlin"))
	implementation("androidx.recyclerview:recyclerview:1.2.0-alpha05")
	testImplementation("junit:junit:4.13")
	androidTestImplementation("androidx.test.ext:junit:1.1.2")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
