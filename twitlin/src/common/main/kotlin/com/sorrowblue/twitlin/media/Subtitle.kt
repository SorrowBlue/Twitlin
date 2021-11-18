package com.sorrowblue.twitlin.media

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.MediaId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Subtitle
 *
 * @property mediaId
 * @property languageCode
 * @property displayName
 * @constructor Create empty Subtitle
 */
@AndroidParcelize
@Serializable
public data class Subtitle(
    @SerialName("media_id") val mediaId: MediaId,
    @SerialName("language_code") val languageCode: String,
    @SerialName("display_name") val displayName: String
) : AndroidParcelable, JvmSerializable
