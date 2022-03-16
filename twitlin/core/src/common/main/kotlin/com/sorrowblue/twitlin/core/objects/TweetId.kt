package com.sorrowblue.twitlin.core.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
@AndroidParcelize
public value class TweetId(public override val id: String) : UUID, AndroidParcelable
