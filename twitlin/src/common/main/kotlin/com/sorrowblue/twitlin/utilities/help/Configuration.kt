package com.sorrowblue.twitlin.utilities.help

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.Entities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Configuration
 *
 * @property charactersReservedPerMedia
 * @property maxMediaPerUpload
 * @property dmTextCharacterLimit
 * @property photoSizeLimit
 * @property photoSizes
 * @property shortUrlLength
 * @property shortUrlLengthHttps
 * @property nonUsernamePaths
 * @constructor Create empty Configuration
 */
@AndroidParcelize
@Serializable
public data class Configuration(
    @SerialName("characters_reserved_per_media")
    val charactersReservedPerMedia: Int,
    @SerialName("max_media_per_upload")
    val maxMediaPerUpload: Int,
    @SerialName("dm_text_character_limit")
    val dmTextCharacterLimit: Int,
    @SerialName("photo_size_limit")
    val photoSizeLimit: Int,
    @SerialName("photo_sizes")
    val photoSizes: Entities.Media.MediaSize,
    @SerialName("short_url_length")
    val shortUrlLength: Int,
    @SerialName("short_url_length_https")
    val shortUrlLengthHttps: Int,
    @SerialName("non_username_paths")
    val nonUsernamePaths: List<String>
) : AndroidParcelable, JvmSerializable
