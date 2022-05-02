package com.sorrowblue.twitlin.api.users.savedsearches

import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SavedSearch(
    @SerialName("created_at") val _createdAt: String,
    val id: Long,
    @SerialName("id_str") val idStr: String,
    val name: String,
    val position: String?,
    val query: String
) : JvmSerializable {

    val createdAt: LocalDateTime get() = _createdAt.rfc822ToLocalDateTime()
}
