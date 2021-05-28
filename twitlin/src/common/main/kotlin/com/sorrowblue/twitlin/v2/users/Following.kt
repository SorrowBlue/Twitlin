package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@AndroidParcelize
public data class Following(
    val following: Boolean,
    @SerialName("pending_follow") val pendingFollow: Boolean
) : AndroidParcelable, JvmSerializable
