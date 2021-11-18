package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.objects.UUID
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
@JvmInline
public value class SpaceId(override val id: String) : UUID, AndroidParcelable
