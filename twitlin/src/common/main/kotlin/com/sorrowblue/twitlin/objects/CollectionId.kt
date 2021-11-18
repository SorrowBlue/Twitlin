package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * Collection id
 *
 * @property id
 * @constructor Create empty Collection id
 */
@JvmInline
@Serializable
@AndroidParcelize
public value class CollectionId(override val id: String) : UUID, AndroidParcelable
