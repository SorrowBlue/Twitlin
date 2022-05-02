package com.sorrowblue.twitlin.api.application

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Rate limit status
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
     * Rate limit context
     *
     * @property application
     * @property accessToken
     */
    @AndroidParcelize
    @Serializable
    public data class RateLimitContext(
        val application: String? = null,
        @SerialName("access_token")
        val accessToken: String? = null
    ) : AndroidParcelable, JvmSerializable

    /**
     * Resource
     *
     * @property family
     * @property info
     */
    @AndroidParcelize
    public data class Resource(
        val family: String,
        val info: List<Info>
    ) : AndroidParcelable, JvmSerializable {

        /**
         * Info
         *
         * @property path
         * @property limit
         * @property remaining
         * @property _reset
         */
        @AndroidParcelize
        public data class Info(
            val path: String,
            val limit: Int,
            val remaining: Int,
            internal val _reset: Long,
        ) : AndroidParcelable, JvmSerializable {

            @KotlinIgnoredOnParcel
            val reset: LocalDateTime
                get() = _reset.epochToLocalDateTime()
        }
    }
}
