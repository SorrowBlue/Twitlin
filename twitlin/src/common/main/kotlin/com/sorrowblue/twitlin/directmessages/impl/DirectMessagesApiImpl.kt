package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.directmessages.CallToAction
import com.sorrowblue.twitlin.directmessages.DirectMessage
import com.sorrowblue.twitlin.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.directmessages.PagingDirectMessage
import com.sorrowblue.twitlin.directmessages.QuickReply
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest.Event.MessageCreate
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest.Event.MessageCreate.Target
import com.sorrowblue.twitlin.directmessages.request.MessageDataRequest
import com.sorrowblue.twitlin.objects.MediaId
import com.sorrowblue.twitlin.utils.API_DIRECT_MESSAGES
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.builtins.serializer

internal class DirectMessagesApiImpl(private val client: TwitterClient) : DirectMessagesApi {

    override suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply?,
        ctas: List<CallToAction>?,
        mediaId: MediaId?
    ): Response<DirectMessage> {
        return client.post("$API_DIRECT_MESSAGES/events/new.json", Response.serializer(DirectMessage.serializer())) {
            contentType(ContentType.Application.Json)
            val attachment = mediaId?.let { MessageDataRequest.Attachment.Media(it) }
                ?.let { MessageDataRequest.Attachment("media", it) }
            val dmData = MessageDataRequest(text, attachment, quickReply, ctas)
            body = DirectMessageRequest(
                DirectMessageRequest.Event(
                    "message_create",
                    MessageCreate(Target(recipientId), dmData)
                )
            )
        }
    }

    override suspend fun list(count: Int?, cursor: String?): Response<PagingDirectMessage> {
        return client.get("$API_DIRECT_MESSAGES/events/list.json", Response.serializer(PagingDirectMessage.serializer())) {
            "count" to count
            "cursor" to cursor
        }
    }

    override suspend fun show(id: String): Response<DirectMessage> {
        return client.get("$API_DIRECT_MESSAGES/events/show.json", Response.serializer(DirectMessage.serializer())) {
            "id" to id
        }
    }

    override suspend fun destroy(id: String): Response<DirectMessage> {
        return client.delete("$API_DIRECT_MESSAGES/events/destroy.json", Response.serializer(DirectMessage.serializer())) {
            "id" to id
        }
    }

    @Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
    override suspend fun indicateTyping(receiveId: String): Response<Unit> {
        return client.submitForm(
            "$API_DIRECT_MESSAGES/indicate_typing.json",
            Response.serializer(Unit.serializer()),
            Parameters.build {
                append("recipient_id", receiveId)
            })
    }

    @Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
    override suspend fun markRead(lastReadEventId: String, recipientId: String): Response<Unit> {
        return client.submitForm(
            "$API_DIRECT_MESSAGES/mark_read.json",
            Response.serializer(Unit.serializer()), Parameters.build {
                append("last_read_event_id", lastReadEventId)
                append("recipient_id", recipientId)
            })
    }
}
