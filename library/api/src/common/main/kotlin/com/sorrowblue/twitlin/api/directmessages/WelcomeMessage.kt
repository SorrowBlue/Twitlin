package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class WelcomeMessage(
    val id: String,
    @SerialName("created_timestamp")
    internal val _createdTimestamp: String,
    @SerialName("message_data")
    val messageData: MessageData,
    @SerialName("source_app_id")
    val sourceAppId: String,
    val name: String
) : AndroidParcelable, JvmSerializable {

    val createdTimestamp: LocalDateTime get() = _createdTimestamp.epochToLocalDateTime()
}
