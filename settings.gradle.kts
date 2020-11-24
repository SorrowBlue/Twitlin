pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		google()
		jcenter()
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
				useModule("com.android.tools.build:gradle:4.0.2")
			}
		}
	}
}
4

include(":app")
include(":twitlin")
