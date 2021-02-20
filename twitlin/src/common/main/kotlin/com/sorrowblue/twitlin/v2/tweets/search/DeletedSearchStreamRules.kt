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
 * @property meta
 * @property errors
 */
@Serializable
public data class DeletedSearchStreamRules(val meta: Meta, val errors: List<Error>? = null) :
    JvmSerializable {

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
         * @property deleted
         * @property notDeleted
         */
        @Serializable
        public data class Summary(
            val deleted: Int,
            @SerialName("not_deleted")
            val notDeleted: Int
        ) : JvmSerializable
    }
}
