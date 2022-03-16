package com.sorrowblue.twitlin.api.users.friendships.impl

import com.sorrowblue.twitlin.api.users.friendships.Relationship
import kotlinx.serialization.Serializable

@Serializable
internal class RelationshipDetailResponse(val relationship: Relationship)
