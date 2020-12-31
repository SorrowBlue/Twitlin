/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property source
 * @property target
 */
@Serializable
public data class RelationshipDetail(val source: Detail, val target: Detail) : JvmSerializable {

    /**
     * TODO
     *
     * @property id
     * @property idStr
     * @property screenName
     * @property following
     * @property followedBy
     * @property liveFollowing
     * @property followingReceived
     * @property followingRequested
     * @property notificationsEnabled
     * @property canDm
     * @property blocking
     * @property blockedBy
     * @property muting
     * @property wantRetweets
     * @property allReplies
     * @property markedSpam
     */
    @Serializable
    public data class Detail(
        val id: Long,
        @SerialName("id_str") val idStr: String,
        @SerialName("screen_name") val screenName: String,
        val following: Boolean,
        @SerialName("followed_by") val followedBy: Boolean,
        @SerialName("live_following") val liveFollowing: Boolean? = null,
        @SerialName("following_received") val followingReceived: Boolean? = null,
        @SerialName("following_requested") val followingRequested: Boolean? = null,
        @SerialName("notifications_enabled") val notificationsEnabled: Boolean? = null,
        @SerialName("can_dm") val canDm: Boolean? = null,
        val blocking: Boolean? = null,
        @SerialName("blocked_by") val blockedBy: Boolean? = null,
        val muting: Boolean? = null,
        @SerialName("want_retweets") val wantRetweets: Boolean? = null,
        @SerialName("all_replies") val allReplies: Boolean? = null,
        @SerialName("marked_spam") val markedSpam: Boolean? = null
    ) : JvmSerializable
}
