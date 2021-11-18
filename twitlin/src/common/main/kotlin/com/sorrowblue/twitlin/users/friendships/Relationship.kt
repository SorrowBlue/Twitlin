package com.sorrowblue.twitlin.users.friendships

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Relationship
 *
 * @property source
 * @property target
 * @constructor Create empty Relationship
 */
@AndroidParcelize
@Serializable
public data class Relationship(val source: Detail, val target: Detail) : AndroidParcelable, JvmSerializable {

    /**
     * Detail
     *
     * @property id
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
     * @constructor Create empty Detail
     */
    @AndroidParcelize
    @Serializable
    public data class Detail(
        @SerialName("id_str")
        val id: UserId,
        @SerialName("screen_name")
        val screenName: String,
        val following: Boolean,
        @SerialName("followed_by")
        val followedBy: Boolean,
        @SerialName("live_following")
        val liveFollowing: Boolean? = null,
        @SerialName("following_received")
        val followingReceived: Boolean? = null,
        @SerialName("following_requested")
        val followingRequested: Boolean? = null,
        @SerialName("notifications_enabled")
        val notificationsEnabled: Boolean? = null,
        @SerialName("can_dm")
        val canDm: Boolean? = null,
        val blocking: Boolean? = null,
        @SerialName("blocked_by")
        val blockedBy: Boolean? = null,
        val muting: Boolean? = null,
        @SerialName("want_retweets")
        val wantRetweets: Boolean? = null,
        @SerialName("all_replies")
        val allReplies: Boolean? = null,
        @SerialName("marked_spam")
        val markedSpam: Boolean? = null
    ) : AndroidParcelable, JvmSerializable
}
