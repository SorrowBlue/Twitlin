/*
 * (c) 2020-2021 SorrowBlue.
 */

import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class MavenCentralRepository : Plugin<Project> {

    override fun apply(target: Project) = target.execute()

    private fun Project.execute() {
        apply(plugin = "maven-publish")
        apply(plugin = "signing")
        apply(plugin = "io.codearte.nexus-staging")
        val androidSourcesJar = tasks.register<Jar>("androidSourcesJar") {
            archiveClassifier.set("source")
            ((this as org.gradle.api.plugins.ExtensionAware).extensions.findByName("android") as? com.android.build.gradle.internal.dsl.BaseAppModuleExtension)
                ?.sourceSets?.get("main")?.java?.srcDirs?.let {
                    from(it)
                }
        }

        artifacts {
            add("archives", androidSourcesJar)
        }

        loadExt()

        ext.properties.forEach { (s, any) ->
            println("$s: $any")
        }

        nexusStaging {
            packageGroup = ext["PUBLISH_GROUP_ID"].toString()
            stagingProfileId = ext["sonatypeStagingProfileId"].toString()
            username = ext["ossrhUsername"].toString()
            password = ext["ossrhPassword"].toString()
        }

        publishing {
            publications.withType<DefaultMavenPublication>().all {
                if (name.contains("ios").not() && name != "kotlinMultiPlatform") {
                    setModuleDescriptorGenerator(null)
                }
                pom {
                    name.set(artifactId)
                    description.set("Twitlin for Twitter API")
                    url.set("https://github.com/SorrowBlue/Twitlin")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("sorrowblue_sb")
                            name.set("Sorrow Blue")
                            email.set("sorrowblue.sb@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/SorrowBlue/Twitlin.git")
                        developerConnection.set("scm:git:ssh://github.com/SorrowBlue/Twitlin.git")
                        url.set("https://github.com/SorrowBlue/Twitlin/tree/main")
                    }
                }
            }
            repositories {
                maven {
                    name = "sonatype"
                    val releasesUrl =
                        "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsUrl =
                        "https://oss.sonatype.org/content/repositories/snapshots/"
                    val uploadUri =
                        if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
                    url = uri(uploadUri)

                    credentials {
                        username = ext["ossrhUsername"].toString()
                        password = ext["ossrhPassword"].toString()
                    }
                }
            }
        }
        signing {
            sign(publishing.publications)
        }
    }

    private fun Project.loadExt() {
        ext {
            val p = Properties()
            val secretPropsFile = project.rootProject.file("local.properties")
            if (secretPropsFile.exists()) {
                println("Found secret props file, loading props")
                p.load(FileInputStream(secretPropsFile))
            }
            println("No props file, loading env vars")
            ext["signing.keyId"] =
                p.getProperty("signing.keyId") ?: System.getenv("SIGNING_KEY_ID")
            ext["signing.password"] =
                p.getProperty("signing.password") ?: System.getenv("SIGNING_PASSWORD")
            ext["signing.secretKeyRingFile"] =
                p.getProperty("signing.secretKeyRingFile")
                    ?: System.getenv("SIGNING_SECRET_KEY_RING_FILE")
            ext["ossrhUsername"] =
                p.getProperty("ossrhUsername") ?: System.getenv("OSSRH_USERNAME")
            ext["ossrhPassword"] =
                p.getProperty("ossrhPassword") ?: System.getenv("OSSRH_PASSWORD")
            ext["sonatypeStagingProfileId"] =
                p.getProperty("sonatypeStagingProfileId")
                    ?: System.getenv("SONATYPE_STAGING_PROFILE_ID")
        }
    }
}
