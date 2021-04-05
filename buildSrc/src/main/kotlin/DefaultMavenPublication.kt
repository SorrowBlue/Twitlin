import org.gradle.api.Project
import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication
import org.gradle.kotlin.dsl.extra

fun DefaultMavenPublication.defaultPom() {
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

fun Project.replaceProperty(s: String, s1: String) {
    findProperty(s)?.let { extra[s1] = it }
}

fun String.toVersion() = this + if (matches(".*-[0-9]+-g[0-9a-f]{7}".toRegex())) "-SNAPSHOT" else ""
