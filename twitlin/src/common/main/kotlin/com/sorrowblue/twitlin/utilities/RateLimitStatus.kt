/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property rateLimitContext
 * @property resources
 */
@AndroidParcelize
public data class RateLimitStatus(
    val rateLimitContext: RateLimitContext,
    val resources: List<Resource>
) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property application
     * @property accessToken
     */
    @AndroidParcelize
    @Serializable
    public data class RateLimitContext(
        public val application: String? = null,
        @SerialName("access_token") public val accessToken: String? = null
    ) : AndroidParcelable, JvmSerializable

    /**
     * TODO
     *
     * @property family
     * @property info
     */
    @AndroidParcelize
    public data class Resource(
        val family: ResourceFamily,
        val info: List<Info>
    ) : AndroidParcelable, JvmSerializable {

        /**
         * TODO
         *
         * @property path
         * @property limit
         * @property remaining
         * @property reset
         */
        @AndroidParcelize
        public data class Info(
            val path: String,
            val limit: Int,
            val remaining: Int,
            val _reset: Int,
        ) : AndroidParcelable, JvmSerializable {

            @KotlinIgnoredOnParcel
            val reset: LocalDateTime
                get() = _reset.epochToLocalDateTime()
        }
    }
}
