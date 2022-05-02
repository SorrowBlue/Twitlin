package com.sorrowblue.twitlin.api.v2.tweets.search.impl

import com.sorrowblue.twitlin.api.v2.tweets.search.RuleId
import kotlinx.serialization.Serializable

@Serializable
internal class DeleteSearchStreamRuleRequest(val delete: Delete) {

    constructor(ids: List<RuleId>) : this(Delete(ids))

    @Serializable
    class Delete(val ids: List<RuleId>)
}
