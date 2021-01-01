/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property rateLimitContext
 * @property resources
 */
public data class RateLimitStatus(
    val rateLimitContext: RateLimitContext,
    val resources: List<Resource>
) : JvmSerializable {

    /**
     * TODO
     *
     * @property application
     * @property accessToken
     */
    @Serializable
    public class RateLimitContext(
        public val application: String? = null,
        @SerialName("access_token") public val accessToken: String? = null
    ) : JvmSerializable

    /**
     * TODO
     *
     * @property family
     * @property info
     */
    public data class Resource(
        val family: ResourceFamily,
        val info: List<Info>
    ) : JvmSerializable {

        /**
         * TODO
         *
         * @property path
         * @property limit
         * @property remaining
         * @property reset
         */
        public data class Info(
            val path: String,
            val limit: Int,
            val remaining: Int,
            val reset: LocalDateTime,
        ) : JvmSerializable
    }
}
