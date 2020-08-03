pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		google()
		jcenter()
		maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
				useModule("com.android.tools.build:gradle:4.0.1")
			}
		}
	}
}

rootProject.name = "Twitlin"

include(":app")
include(":twitlin")
