/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search.request

import kotlinx.serialization.Serializable

@Serializable
internal class DeleteSearchStreamRuleRequest(val delete: Delete) {

    constructor(ids: List<String>) : this(Delete(ids))

    @Serializable
    class Delete(val ids: List<String>)
}
