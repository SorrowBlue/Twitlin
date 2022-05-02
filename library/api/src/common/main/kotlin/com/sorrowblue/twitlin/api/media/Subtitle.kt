package com.sorrowblue.twitlin.api.media

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.MediaId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Subtitle
 *
 * @property mediaId
 * @property languageCode
 * @property displayName
 */
@AndroidParcelize
@Serializable
public data class Subtitle(
    @SerialName("media_id")
    val mediaId: MediaId,
    @SerialName("language_code")
    val languageCode: String,
    @SerialName("display_name")
    val displayName: String
) : AndroidParcelable, JvmSerializable
