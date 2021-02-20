/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property data
 * @property meta
 * @property errors
 */
@Serializable
public data class SearchStreamRules(
    val data: List<SearchStreamRule> = emptyList(),
    val meta: Meta
) : JvmSerializable {

    /**
     * TODO
     *
     * @property sent
     */
    @Serializable
    public data class Meta(
        @Serializable(LocalDateTimeISOSerializer::class)
        val sent: LocalDateTime
    ) : JvmSerializable
}
