package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.objects.Language
import kotlinx.serialization.Serializable

interface HelpApi {
	suspend fun configuration(): Response<Configuration>
	suspend fun languages(): Response<List<Language>>
}

@Serializable
data class Configuration(
	val characters_reserved_per_media: Int,
	val max_media_per_upload: Int,
	val dm_text_character_limit: Int,
	val photo_size_limit: Int,
	val photo_sizes: Entities.Media.MediaSize,
	val short_url_length: Int,
	val short_url_length_https: Int,
	val non_username_paths: List<String>
)
