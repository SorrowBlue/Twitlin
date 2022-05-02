package com.sorrowblue.twitlin.api.v2.spaces

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.objects.UUID
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
@JvmInline
public value class SpaceId(override val id: String) : UUID, AndroidParcelable
