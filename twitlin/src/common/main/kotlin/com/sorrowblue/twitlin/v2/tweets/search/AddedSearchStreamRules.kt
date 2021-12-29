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
 * Added search stream rules
 *
 * @property data
 * @property meta
 * @property errors
 * @constructor Create empty Added search stream rules
 */
@AndroidParcelize
@Serializable
public data class AddedSearchStreamRules(
    val data: List<SearchStreamRule>? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * Meta
     *
     * @property _sent
     * @property summary
     * @constructor Create empty Meta
     */
    @AndroidParcelize
    @Serializable
    public data class Meta(
        @SerialName("sent") val _sent: String,
        val summary: Summary
    ) : AndroidParcelable, JvmSerializable {

        /**
         * Sent
         */
        val sent: LocalDateTime get() = _sent.isoToLocalDateTime()

        /**
         * Summary
         *
         * @property created
         * @property notCreated
         * @property valid
         * @property invalid
         * @constructor Create empty Summary
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
