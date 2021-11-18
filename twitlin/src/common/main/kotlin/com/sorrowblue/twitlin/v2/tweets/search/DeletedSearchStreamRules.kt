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
 * @property meta
 * @property errors
 */
@AndroidParcelize
@Serializable
public data class DeletedSearchStreamRules(val meta: Meta, val errors: List<Error>? = null) :
    AndroidParcelable, JvmSerializable {

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
         * @property deleted
         * @property notDeleted
         */
        @AndroidParcelize
        @Serializable
        public data class Summary(val deleted: Int, @SerialName("not_deleted") val notDeleted: Int) :
            AndroidParcelable, JvmSerializable
    }
}
