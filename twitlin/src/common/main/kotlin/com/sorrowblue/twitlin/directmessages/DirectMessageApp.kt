package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class DirectMessageApp(
    val id: String,
    val name: String,
    val url: String
) : AndroidParcelable, JvmSerializable
