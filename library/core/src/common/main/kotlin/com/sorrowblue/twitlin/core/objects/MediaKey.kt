package com.sorrowblue.twitlin.core.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * Media key
 *
 * @property id
 */
@JvmInline
@Serializable
@AndroidParcelize
public value class MediaKey(override val id: String) : UUID, AndroidParcelable
