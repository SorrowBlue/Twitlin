/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStrEpochSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WelcomeMessage(
    val id: String,
    @Serializable(LocalDateTimeStrEpochSerializer::class)
    @SerialName("created_timestamp")
    val createdTimestamp: LocalDateTime,
    @SerialName("message_data")
    val messageData: MessageData,
    @SerialName("source_app_id")
    val sourceAppId: String,
    val name: String
)
