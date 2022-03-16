package com.sorrowblue.twitlin.api.tweets.collections.impl

import com.sorrowblue.twitlin.api.objects.CollectionId
import com.sorrowblue.twitlin.api.tweets.collections.CollectionChange
import kotlinx.serialization.Serializable

@Serializable
internal class CreateEntriesRequest(val id: CollectionId, val changes: List<CollectionChange>)
