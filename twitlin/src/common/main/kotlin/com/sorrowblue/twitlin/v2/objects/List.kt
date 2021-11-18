package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class List(
    val id: ListId,
    val name: String,
    val created_at: String? = null,
    @SerialName("private") val isPrivate: Boolean? = null,
    @SerialName("follower_count") val followerCount: Int? = null,
    @SerialName("member_count") val memberCount: Int? = null,
    @SerialName("owner_id") val ownerId: UserId? = null,
    @SerialName("description") val description: String? = null
) : JvmSerializable, AndroidParcelable
