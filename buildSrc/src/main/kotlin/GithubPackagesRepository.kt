/*
 * (c) 2020-2021 SorrowBlue.
 */

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType

class GithubPackagesRepository : Plugin<Project> {

    override fun apply(target: Project) = target.execute()

    private fun Project.execute() {
        apply(plugin = "kotlin-multiplatform")
        apply(plugin = "maven-publish")
        publishing {
            publications.withType<DefaultMavenPublication>().all {
                if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                    setModuleDescriptorGenerator(null)
                }
            }
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/SorrowBlue/Twitlin")
                    credentials {
                        username =
                            project.findProperty("gpr.user")?.toString()
                                ?: System.getenv("GITHUB_USERNAME")
                        password =
                            project.findProperty("gpr.token")?.toString()
                                ?: System.getenv("GITHUB_TOKEN")
                    }
                }
            }
        }
    }
}
