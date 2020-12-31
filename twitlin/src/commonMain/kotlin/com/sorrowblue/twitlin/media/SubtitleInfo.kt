/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property mediaId
 * @property languageCode
 * @property displayName
 */
@Serializable
public data class SubtitleInfo(
    @SerialName("media_id")
    val mediaId: String, @SerialName("language_code") val languageCode: String,
    @SerialName("display_name") val displayName: String
)
