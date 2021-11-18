package com.sorrowblue.twitlin.tweets.collections

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionDestroy(val destroyed: Boolean) : AndroidParcelable, JvmSerializable
