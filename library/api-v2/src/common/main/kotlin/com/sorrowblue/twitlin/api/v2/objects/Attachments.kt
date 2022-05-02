package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.MediaKey
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Attachments
 *
 * @property pollIds
 * @property mediaKeys
 * @constructor Create empty Attachments
 */
@AndroidParcelize
@Serializable
public data class Attachments(
    @SerialName("poll_ids")
    val pollIds: List<PollId> = emptyList(),
    @SerialName("media_keys")
    val mediaKeys: List<MediaKey> = emptyList()
) : AndroidParcelable, JvmSerializable
