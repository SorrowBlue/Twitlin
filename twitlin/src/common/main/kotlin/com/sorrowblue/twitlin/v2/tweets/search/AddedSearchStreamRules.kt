/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property data
 * @property meta
 * @property errors
 */
@AndroidParcelize
@Serializable
public data class AddedSearchStreamRules(
    val data: List<SearchStreamRule>? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property sent
     * @property summary
     */
    @AndroidParcelize
    @Serializable
    public data class Meta(val _sent: String, val summary: Summary) : AndroidParcelable, JvmSerializable {

        val sent: LocalDateTime get() = _sent.isoToLocalDateTime()

        /**
         * TODO
         *
         * @property created
         * @property notCreated
         * @property valid
         * @property invalid
         */
        @AndroidParcelize
        @Serializable
        public data class Summary(
            val created: Int,
            @SerialName("not_created") val notCreated: Int,
            val valid: Int,
            val invalid: Int
        ) : AndroidParcelable, JvmSerializable
    }
}
