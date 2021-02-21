import io.codearte.gradle.nexus.NexusStagingExtension
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.publish.Publication
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/*
 * (c) 2020-2021 SorrowBlue.
 */

fun Project.nexusStaging(function: NexusStagingExtension.() -> Unit) = function(extensions.getByType())

fun Project.publishing(function: PublishingExtension.() -> Unit) =
    function(extensions.getByType())

fun Project.signing(function: SigningExtension.() -> Unit) = function(extensions.getByType())

val Project.publishing
    get() = extensions.getByType<PublishingExtension>()

fun Project.ext(configure: Action<ExtraPropertiesExtension>): Unit =
    (this as ExtensionAware).extensions.configure("ext", configure)

val Project.ext get() = extensions.getByType<ExtraPropertiesExtension>()

fun <T> PublicationContainer.all(action: T.(Publication) -> Unit) = all {
    @Suppress("UNCHECKED_CAST")
    action(this as T, this)
}

//internal val Project.kotlin: KotlinJvmProjectExtension get() =
//    (this as ExtensionAware).extensions.getByName("kotlin") as KotlinJvmProjectExtension

val NamedDomainObjectContainer<KotlinSourceSet>.commonMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named<KotlinSourceSet>("commonMain")

val Project.sourceSets: SourceSetContainer get() =
    (this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer
