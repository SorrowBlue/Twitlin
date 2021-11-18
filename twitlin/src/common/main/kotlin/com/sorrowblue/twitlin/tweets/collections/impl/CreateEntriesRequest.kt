package com.sorrowblue.twitlin.tweets.collections.impl

import com.sorrowblue.twitlin.objects.CollectionId
import com.sorrowblue.twitlin.tweets.collections.CollectionChange
import kotlinx.serialization.Serializable

@Serializable
internal class CreateEntriesRequest(val id: CollectionId, val changes: List<CollectionChange>)
