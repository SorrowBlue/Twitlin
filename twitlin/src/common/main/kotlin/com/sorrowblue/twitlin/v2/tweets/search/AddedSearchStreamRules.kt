/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property data
 * @property meta
 * @property errors
 */
@Serializable
public data class AddedSearchStreamRules(
    val data: List<SearchStreamRule>? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) : JvmSerializable {

    /**
     * TODO
     *
     * @property sent
     * @property summary
     */
    @Serializable
    public data class Meta(
        @Serializable(LocalDateTimeISOSerializer::class)
        val sent: LocalDateTime,
        val summary: Summary
    ) : JvmSerializable {

        /**
         * TODO
         *
         * @property created
         * @property notCreated
         * @property valid
         * @property invalid
         */
        @Serializable
        public data class Summary(
            val created: Int,
            @SerialName("not_created")
            val notCreated: Int,
            val valid: Int,
            val invalid: Int
        ) : JvmSerializable
    }
}
