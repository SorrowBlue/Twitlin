/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.directmessages.PagingWelcomeMessage
import com.sorrowblue.twitlin.directmessages.PagingWelcomeMessageRule
import com.sorrowblue.twitlin.directmessages.QuickReply
import com.sorrowblue.twitlin.directmessages.WelcomeMessageData
import com.sorrowblue.twitlin.directmessages.WelcomeMessageRule
import com.sorrowblue.twitlin.directmessages.WelcomeMessagesApi
import com.sorrowblue.twitlin.directmessages.request.MessageDataRequest
import com.sorrowblue.twitlin.directmessages.request.WelcomeMessageRequest
import com.sorrowblue.twitlin.directmessages.request.WelcomeMessageRuleRequest
import com.sorrowblue.twitlin.directmessages.response.WelcomeMessageRuleResponse

private const val WELCOME_MESSAGES = "${Urls.V1}/direct_messages/welcome_messages"

internal class WelcomeMessagesApiImpl(private val client: UserClient) : WelcomeMessagesApi {
    override suspend fun destroy(id: String): Response<Unit> =
        client.delete("$WELCOME_MESSAGES/destroy.json", "id" to id)

    override suspend fun destroyRule(id: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun show(id: String): Response<WelcomeMessageData> =
        client.get("$WELCOME_MESSAGES/show.json", "id" to id)

    override suspend fun showRule(id: String): Response<WelcomeMessageRule> =
        client.get<WelcomeMessageRuleResponse>("$WELCOME_MESSAGES/rules/show.json", "id" to id)
            .convertData(WelcomeMessageRuleResponse::welcome_message_rule)

    override suspend fun listRule(count: Int, cursor: String?): Response<PagingWelcomeMessageRule> =
        client.get("$WELCOME_MESSAGES/rules/list.json", "count" to count, "cursor" to cursor)

    override suspend fun list(count: Int, cursor: String?): Response<PagingWelcomeMessage> =
        client.get("$WELCOME_MESSAGES/list.json", "count" to count, "cursor" to cursor)

    override suspend fun new(
        name: String,
        text: String,
        mediaId: String?,
        quickReply: QuickReply?
    ): Response<WelcomeMessageData> {
        val attachment = mediaId?.let {
            MessageDataRequest.Attachment("media", MessageDataRequest.Attachment.Media(id = it))
        }
        val messageDataRequest = MessageDataRequest(text, attachment, quickReply)
        return client.postJson(
            "$WELCOME_MESSAGES/new.json", clazz = WelcomeMessageRequest(
                WelcomeMessageRequest.WelcomeMessage(name, messageDataRequest)
            )
        )
    }

    override suspend fun newRule(welcomeMessageId: String): Response<WelcomeMessageRule> =
        client.postJson(
            "$WELCOME_MESSAGES/rules/new.json",
            clazz = WelcomeMessageRuleRequest(
                WelcomeMessageRuleRequest.WelcomeMessageRuleRequest(
                    welcomeMessageId
                )
            )
        )

    override suspend fun update(
        id: String,
        text: String,
        mediaId: String?,
        quickReply: QuickReply?
    ): Response<WelcomeMessageData> {
        val attachment = mediaId?.let {
            MessageDataRequest.Attachment("media", MessageDataRequest.Attachment.Media(id = it))
        }
        val messageDataRequest = MessageDataRequest(text, attachment, quickReply)
        return client.putJson(
            "$WELCOME_MESSAGES/update.json", "id" to id,
            clazz = WelcomeMessageRequest.WelcomeMessage("", messageDataRequest)
        )
    }
}
