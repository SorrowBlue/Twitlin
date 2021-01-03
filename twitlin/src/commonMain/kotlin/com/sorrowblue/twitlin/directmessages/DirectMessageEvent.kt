/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStrEpochSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class DirectMessageEvent(
    val type: String,
    val id: String,
    @Serializable(LocalDateTimeStrEpochSerializer::class)
    val created_timestamp: LocalDateTime,
    val message_create: MessageCreate
) {

    @Serializable
    public data class MessageCreate(
        val target: Target,
        val sender_id: String,
        val source_app_id: String? = null,
        val message_data: MessageData
    ) {

        @Serializable
        public data class Target(val recipient_id: String)
    }
}
