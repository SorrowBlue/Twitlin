/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.User
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeRFC822Serializer
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
@Serializable
public data class UserList(
    val id: Long,
    @SerialName("id_str")
    val idStr: String,
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
    @Serializable(LocalDateTimeRFC822Serializer::class)
    val createdAt: LocalDateTime,
    val following: Boolean,
    val user: User
) : JvmSerializable {

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
