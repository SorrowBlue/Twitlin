import org.gradle.api.publish.maven.internal.publication.DefaultMavenPublication

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
