/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property name
 * @property screenName
 * @property id
 * @property idStr
 * @property connections
 */
@Serializable
public data class Relationship(
    val name: String,
    @SerialName("screen_name") val screenName: String,
    val id: Long,
    @SerialName("id_str") val idStr: String,
    val connections: List<Connection>
) : JvmSerializable {

    /**
     * TODO
     */
    @Serializable
    public enum class Connection {

        /**
         * TODO
         */
        @SerialName("following")
        FOLLOWING,

        /**
         * TODO
         */
        @SerialName("following_requested")
        FOLLOWING_REQUESTED,

        /**
         * TODO
         */
        @SerialName("followed_by")
        FOLLOWED_BY,

        /**
         * TODO
         */
        @SerialName("none")
        NONE,

        /**
         * TODO
         */
        @SerialName("blocking")
        BLOCKING,

        /**
         * TODO
         */
        @SerialName("muting")
        MUTING
    }
}
