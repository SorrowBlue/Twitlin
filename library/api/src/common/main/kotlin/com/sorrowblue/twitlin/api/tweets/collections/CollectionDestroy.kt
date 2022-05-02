package com.sorrowblue.twitlin.api.tweets.collections

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionDestroy(val destroyed: Boolean) : AndroidParcelable, JvmSerializable
