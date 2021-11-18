/*
 * (c) 2020-2021 SorrowBlue.
 */

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Twitlin"

include(":app")
include(":twitlin")
