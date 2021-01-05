/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.request

import com.sorrowblue.twitlin.tweets.CollectionChange
import kotlinx.serialization.Serializable

@Serializable
internal class CurateEntriesRequest(val id: String, val changes: List<CollectionChange>)
