/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeRFC822Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SavedSearch(
    @Serializable(LocalDateTimeRFC822Serializer::class)
    @SerialName("created_at") val createdAt: LocalDateTime,
    val id: Long,
    @SerialName("id_str") val idStr: String,
    val name: String,
    val position: String?,
    val query: String
) : JvmSerializable
