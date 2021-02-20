/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search.request

import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRule
import kotlinx.serialization.Serializable

@Serializable
internal class AddSearchStreamRuleRequest(val add: List<SearchStreamRule>)
