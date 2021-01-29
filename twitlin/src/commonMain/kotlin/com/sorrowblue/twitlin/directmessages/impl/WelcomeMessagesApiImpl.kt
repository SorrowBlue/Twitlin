/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
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
import kotlinx.serialization.builtins.serializer

private const val WELCOME_MESSAGES = "${Urls.V1}/direct_messages/welcome_messages"

internal class WelcomeMessagesApiImpl(private val client: UserClient) : WelcomeMessagesApi {

    override suspend fun destroy(id: String): Response<Unit> {
        return client.delete(
            "$WELCOME_MESSAGES/destroy.json",
            Response.serializer(Unit.serializer()),
            "id" to id
        )
    }

    override suspend fun destroyRule(id: String): Response<Unit> {
        return client.delete(
            "$WELCOME_MESSAGES/rules/destroy.json",
            Response.serializer(Unit.serializer()),
            "id" to id
        )
    }

    override suspend fun show(id: String): Response<WelcomeMessageData> {
        return client.get(
            "$WELCOME_MESSAGES/show.json",
            Response.serializer(WelcomeMessageData.serializer()),
            "id" to id
        )
    }

    override suspend fun showRule(id: String): Response<WelcomeMessageRule> {
        return client.get(
            "$WELCOME_MESSAGES/rules/show.json",
            Response.serializer(WelcomeMessageRuleResponse.serializer()),
            "id" to id
        ).convertData(WelcomeMessageRuleResponse::welcome_message_rule)
    }

    override suspend fun listRule(count: Int, cursor: String?): Response<PagingWelcomeMessageRule> {
        return client.get(
            "$WELCOME_MESSAGES/rules/list.json",
            Response.serializer(PagingWelcomeMessageRule.serializer()),
            "count" to count,
            "cursor" to cursor
        )
    }

    override suspend fun list(count: Int, cursor: String?): Response<PagingWelcomeMessage> {
        return client.get(
            "$WELCOME_MESSAGES/list.json",
            Response.serializer(PagingWelcomeMessage.serializer()),
            "count" to count,
            "cursor" to cursor
        )
    }

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
            "$WELCOME_MESSAGES/new.json",
            Response.serializer(WelcomeMessageData.serializer()),
            WelcomeMessageRequest(WelcomeMessageRequest.WelcomeMessage(name, messageDataRequest))
        )
    }

    override suspend fun newRule(welcomeMessageId: String): Response<WelcomeMessageRule> {
        return client.postJson(
            "$WELCOME_MESSAGES/rules/new.json",
            Response.serializer(WelcomeMessageRuleResponse.serializer()),
            WelcomeMessageRuleRequest(WelcomeMessageRuleRequest.WelcomeMessageRule(welcomeMessageId))
        ).convertData(WelcomeMessageRuleResponse::welcome_message_rule)
    }

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
            "$WELCOME_MESSAGES/update.json",
            Response.serializer(WelcomeMessageData.serializer()),
            WelcomeMessageRequest.WelcomeMessage("", messageDataRequest),
            "id" to id
        )
    }
}
