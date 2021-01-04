/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.directmessages.CallToAction
import com.sorrowblue.twitlin.directmessages.DirectMessage
import com.sorrowblue.twitlin.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.directmessages.PagingDirectMessage
import com.sorrowblue.twitlin.directmessages.QuickReply
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest.Event
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest.Event.MessageCreate
import com.sorrowblue.twitlin.directmessages.request.DirectMessageRequest.Event.MessageCreate.Target
import com.sorrowblue.twitlin.directmessages.request.MessageDataRequest

private const val DIRECT_MESSAGES = "${Urls.V1}/direct_messages"

internal class DirectMessagesApiImpl(private val client: UserClient) : DirectMessagesApi {

    override suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply?,
        ctas: List<CallToAction>?,
        mediaId: String?
    ): Response<DirectMessage> {
        val attachment = mediaId?.let { MessageDataRequest.Attachment.Media(it) }
            ?.let { MessageDataRequest.Attachment("media", it) }
        val dmData = MessageDataRequest(text, attachment, quickReply, ctas)
        val request = DirectMessageRequest(
            Event(
                "message_create",
                MessageCreate(Target(recipientId), dmData)
            )
        )
        return client.postJson("$DIRECT_MESSAGES/events/new.json", clazz = request)
    }

    override suspend fun list(count: Int?, cursor: String?): Response<PagingDirectMessage> =
        client.get("$DIRECT_MESSAGES/events/list.json", "count" to count, "cursor" to cursor)

    override suspend fun show(id: String): Response<DirectMessage> =
        client.get("$DIRECT_MESSAGES/events/show.json", "id" to id)

    override suspend fun destroy(id: String): Response<DirectMessage> =
        client.delete("$DIRECT_MESSAGES/events/destroy.json", "id" to id)

    override suspend fun indicateTyping(receiveId: String): Response<Unit> =
        client.post("$DIRECT_MESSAGES/indicate_typing.json", "recipient_id" to receiveId)

    override suspend fun markRead(lastReadEventId: String, recipientId: String): Response<Unit> =
        client.post(
            "$DIRECT_MESSAGES/mark_read.json",
            "last_read_event_id" to lastReadEventId,
            "recipient_id" to recipientId
        )
}
