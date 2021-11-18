package com.sorrowblue.twitlin.users.friendships

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Friendships
 *
 * @property name
 * @property screenName
 * @property id
 * @property connections
 * @constructor Create empty Friendships
 */
@AndroidParcelize
@Serializable
public data class Friendships(
    @SerialName("id_str")
    val id: UserId,
    val name: String,
    @SerialName("screen_name")
    val screenName: String,
    val connections: List<Connection>
) : AndroidParcelable, JvmSerializable {

    /**
     * Connection
     *
     * @constructor Create empty Connection
     */
    @Serializable
    public enum class Connection {

        /**
         * F o l l o w i n g
         *
         * @constructor Create empty F o l l o w i n g
         */
        @SerialName("following")
        FOLLOWING,

        /**
         * F o l l o w i n g_r e q u e s t e d
         *
         * @constructor Create empty F o l l o w i n g_r e q u e s t e d
         */
        @SerialName("following_requested")
        FOLLOWING_REQUESTED,

        /**
         * F o l l o w e d_b y
         *
         * @constructor Create empty F o l l o w e d_b y
         */
        @SerialName("followed_by")
        FOLLOWED_BY,

        /**
         * N o n e
         *
         * @constructor Create empty N o n e
         */
        @SerialName("none")
        NONE,

        /**
         * B l o c k i n g
         *
         * @constructor Create empty B l o c k i n g
         */
        @SerialName("blocking")
        BLOCKING,

        /**
         * M u t i n g
         *
         * @constructor Create empty M u t i n g
         */
        @SerialName("muting")
        MUTING
    }
}
