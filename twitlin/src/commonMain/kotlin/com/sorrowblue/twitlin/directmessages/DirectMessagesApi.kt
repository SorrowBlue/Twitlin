/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.serializers.LocalDateTimeStrEpochSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public interface DirectMessagesApi {

    public suspend fun newEvents(request: DirectMessageRequest): Response<DirectMessage>
    public suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply? = null,
        mediaId: String? = null
    ): Response<DirectMessage>

    public suspend fun list(
        count: Int? = null,
        cursor: String? = null
    ): Response<PagingDirectMessage>
}

@Serializable
public data class DirectMessageRequest(
    val event: DirectMessageEvent
)

@Serializable
public data class DirectMessageEvent(
    val type: String,
    @SerialName("message_create") val messageCreate: MessageCreate
) {

    @Serializable
    public data class MessageCreate(
        val target: Target,
        @SerialName("message_data") val messageData: MessageData
    ) {
        @Serializable
        public data class Target(
            @SerialName("recipient_id") val recipientId: String
        )

        @Serializable
        public data class MessageData(
            val text: String,
            @SerialName("quick_reply") val quickReply: QuickReply? = null,
            val attachment: Attachment? = null
        ) {

            @Serializable
            public data class Attachment(
                val type: String,
                val media: Media
            ) {

                @Serializable
                public enum class Type {
                    @SerialName("media")
                    MEDIA
                }

                @Serializable
                public data class Media(val id: String)
            }
        }
    }
}

@Serializable
public data class DirectMessage(
    val event: Event
)

@Serializable
public data class PagingDirectMessage(
    val events: List<Event>,
    val apps: Map<String, App>
) {

    @Serializable
    public data class App(val id: String, val name: String, val url: String)


}

@Serializable
public data class Event(
    val type: String,
    val id: String,
    @Serializable(LocalDateTimeStrEpochSerializer::class) val created_timestamp: LocalDateTime,
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

        @Serializable
        public data class MessageData(
            val text: String,
            val entities: Entities,
            val attachment: Attachment? = null,
            val quick_reply: QuickReply? = null,
            val quick_reply_response: QuickReplyResponse? = null
        ) {

            @Serializable
            public data class Attachment(
                val type: String,
                val media: Entities.Media
            )

            @Serializable
            public data class QuickReplyResponse(
                val type: QuickReply.Type,
                val metadata: String
            )
        }
    }
}
