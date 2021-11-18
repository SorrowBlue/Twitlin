package com.sorrowblue.twitlin.v2.tweets.search.impl

import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRule
import kotlinx.serialization.Serializable

@Serializable
internal class AddSearchStreamRuleRequest(val add: List<SearchStreamRule>)
