/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStrEpochSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DirectMessageEvent(
    val type: String,
    val id: String,
    @Serializable(LocalDateTimeStrEpochSerializer::class)
    val created_timestamp: LocalDateTime,
    @SerialName("initiated_via")
    val initiatedVia: InitiatedVia? = null,
    val message_create: MessageCreate
) : JvmSerializable {

    @Serializable
    public data class InitiatedVia(
        @SerialName("tweet_id")
        val tweetId: String,
        @SerialName("welcome_message_id")
        val welcomeMessageId: String
    ) : JvmSerializable

    @Serializable
    public data class MessageCreate(
        val target: Target,
        @SerialName("sender_id")
        val senderId: String,
        @SerialName("source_app_id")
        val sourceAppId: String? = null,
        @SerialName("message_data")
        val messageData: MessageData
    ) : JvmSerializable {

        @Serializable
        public data class Target(
            @SerialName("recipient_id")
            val recipientId: String
        ) : JvmSerializable
    }
}
