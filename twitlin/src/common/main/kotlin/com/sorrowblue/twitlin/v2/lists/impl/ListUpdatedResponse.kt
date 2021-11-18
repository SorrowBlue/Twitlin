package com.sorrowblue.twitlin.v2.lists.impl

import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class DataResponse<T>(val data: T)

@Serializable
internal class ListUpdatedResponse(val updated: Boolean)

@Serializable
internal class ListDeletedResponse(val deleted: Boolean)

@Serializable
internal class ListMemberResponse(@SerialName("is_member") val isMember: Boolean)

@Serializable
internal class ListMemberRequest(@SerialName("user_id") val userId: UserId)

@Serializable
internal class ListFollowOrPinRequest(@SerialName("list_id") val listId: ListId)

@Serializable
internal class ListFollowResponse(val following: Boolean)

@Serializable
internal class ListPinResponse(val pinned: Boolean)
