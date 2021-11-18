package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.MediaKey
import com.sorrowblue.twitlin.objects.PollId
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
