package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
@AndroidParcelize
public value class PollId(override val id: String) : UUID, AndroidParcelable
