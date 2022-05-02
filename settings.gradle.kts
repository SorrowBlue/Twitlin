pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id in listOf("com.android.library", "com.android.application")) {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
            if (requested.id.id in listOf("kotlin-android", "kotlin-parcelize")) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            if (requested.id.id in listOf("kotlin-android", "kotlin-parcelize")) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "Twitlin"

include(":app")
include(":library")
include(":library:core")
include(":library:api")
include(":library:api-v2")
