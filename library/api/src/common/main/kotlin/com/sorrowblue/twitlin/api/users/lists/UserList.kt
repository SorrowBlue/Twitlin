package com.sorrowblue.twitlin.api.users.lists

import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.ListId
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User list
 *
 * @property id
 * @property name
 * @property uri
 * @property subscriberCount
 * @property memberCount
 * @property mode
 * @property description
 * @property slug
 * @property fullName
 * @property _createdAt
 * @property following
 * @property user
 */
@AndroidParcelize
@Serializable
public data class UserList(
    @SerialName("id_str")
    val id: ListId,
    val name: String,
    val uri: String,
    @SerialName("subscriber_count")
    val subscriberCount: Int,
    @SerialName("member_count")
    val memberCount: Int,
    val mode: Mode,
    val description: String,
    val slug: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("created_at")
    internal val _createdAt: String,
    val following: Boolean,
    val user: User
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime get() = _createdAt.rfc822ToLocalDateTime()

    /**
     * TODO
     *
     */
    @Serializable
    public enum class Mode {
        @SerialName("public")
        PUBLIC,

        @SerialName("private")
        PRIVATE
    }
}
