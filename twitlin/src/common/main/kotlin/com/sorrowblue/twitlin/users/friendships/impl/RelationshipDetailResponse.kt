package com.sorrowblue.twitlin.users.friendships.impl

import com.sorrowblue.twitlin.users.friendships.Relationship
import kotlinx.serialization.Serializable

@Serializable
internal class RelationshipDetailResponse(val relationship: Relationship)
