/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property mediaId
 * @property languageCode
 * @property displayName
 */
@AndroidParcelize
@Serializable
public data class Subtitle(
    @SerialName("media_id") val mediaId: String,
    @SerialName("language_code") val languageCode: String,
    @SerialName("display_name") val displayName: String
) : AndroidParcelable, JvmSerializable
