/*
 * (c) 2020-2021 SorrowBlue.
 */

rootProject.name = "Twitlin"

include(":app")
include(":twitlin")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
