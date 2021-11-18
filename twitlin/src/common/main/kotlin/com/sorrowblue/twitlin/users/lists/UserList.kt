package com.sorrowblue.twitlin.users.lists

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.User
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property id
 * @property idStr
 * @property name
 * @property uri
 * @property subscriberCount
 * @property memberCount
 * @property mode
 * @property description
 * @property slug
 * @property fullName
 * @property createdAt
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
    val _createdAt: String,
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
