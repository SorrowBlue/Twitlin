/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.directmessages.DirectMessage
import com.sorrowblue.twitlin.directmessages.DirectMessageEvent
import com.sorrowblue.twitlin.directmessages.DirectMessageRequest
import com.sorrowblue.twitlin.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.directmessages.PagingDirectMessage
import com.sorrowblue.twitlin.directmessages.QuickReply

private const val DIRECT_MESSAGES = "${Urls.V1}/direct_messages"

internal class DirectMessagesApiImpl(private val client: TwitlinClient) : DirectMessagesApi {

    override suspend fun newEvents(request: DirectMessageRequest): Response<DirectMessage> =
        client.postJson("$DIRECT_MESSAGES/events/new.json", clazz = request)

    override suspend fun new(
        recipientId: String,
        text: String,
        quickReply: QuickReply?,
        mediaId: String?
    ): Response<DirectMessage> {
        val request = DirectMessageRequest(
            DirectMessageEvent(
                "message_create", DirectMessageEvent.MessageCreate(
                    DirectMessageEvent.MessageCreate.Target(recipientId),
                    DirectMessageEvent.MessageCreate.MessageData(
                        text,
                        quickReply,
                        mediaId?.let {
                            DirectMessageEvent.MessageCreate.MessageData.Attachment(
                                "media",
                                DirectMessageEvent.MessageCreate.MessageData.Attachment.Media(it)
                            )
                        }
                    )
                )
            )
        )
        return client.postJson("$DIRECT_MESSAGES/events/new.json", clazz = request)
    }

    override suspend fun list(count: Int?, cursor: String?): Response<PagingDirectMessage> =
        client.get("$DIRECT_MESSAGES/events/list.json", "count" to count, "cursor" to cursor)

}
