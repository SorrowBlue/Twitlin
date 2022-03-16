package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.objects.UUID
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * Collection id
 *
 * @property id
 */
@JvmInline
@Serializable
@AndroidParcelize
public value class CollectionId(override val id: String) : UUID, AndroidParcelable
