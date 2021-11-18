package com.sorrowblue.twitlin.directmessages.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
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
import com.sorrowblue.twitlin.objects.MediaId
import com.sorrowblue.twitlin.utils.API_WELCOME_MESSAGES
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.builtins.serializer

internal class WelcomeMessagesApiImpl(private val client: TwitterClient) : WelcomeMessagesApi {

    override suspend fun destroy(id: String): Response<Unit> {
        return client.delete("$API_WELCOME_MESSAGES/destroy.json", Response.serializer(Unit.serializer())) {
            parameter("id", id)
        }
    }

    override suspend fun destroyRule(id: String): Response<Unit> {
        return client.delete("$API_WELCOME_MESSAGES/rules/destroy.json", Response.serializer(Unit.serializer())) {
            parameter("id", id)
        }
    }

    override suspend fun show(id: String): Response<WelcomeMessageData> {
        return client.get("$API_WELCOME_MESSAGES/show.json", Response.serializer(WelcomeMessageData.serializer())) {
            parameter("id", id)
        }
    }

    override suspend fun showRule(id: String): Response<WelcomeMessageRule> {
        return client.get(
            "$API_WELCOME_MESSAGES/rules/show.json",
            Response.serializer(WelcomeMessageRuleResponse.serializer())
        ) {
            parameter("id", id)
        }.convertData(WelcomeMessageRuleResponse::welcome_message_rule)
    }

    override suspend fun listRule(count: Int, cursor: String?): Response<PagingWelcomeMessageRule> {
        return client.get(
            "$API_WELCOME_MESSAGES/rules/list.json",
            Response.serializer(PagingWelcomeMessageRule.serializer())
        ) {
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun list(count: Int, cursor: String?): Response<PagingWelcomeMessage> {
        return client.get("$API_WELCOME_MESSAGES/list.json", Response.serializer(PagingWelcomeMessage.serializer())) {
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun new(
        name: String,
        text: String,
        mediaId: MediaId?,
        quickReply: QuickReply?
    ): Response<WelcomeMessageData> {
        val attachment = mediaId?.let {
            MessageDataRequest.Attachment("media", MessageDataRequest.Attachment.Media(id = it))
        }
        val messageDataRequest = MessageDataRequest(text, attachment, quickReply)
        return client.post("$API_WELCOME_MESSAGES/new.json", Response.serializer(WelcomeMessageData.serializer())) {
            contentType(ContentType.Application.Json)
            body = WelcomeMessageRequest(WelcomeMessageRequest.WelcomeMessage(name, messageDataRequest))
        }
    }

    override suspend fun newRule(welcomeMessageId: String): Response<WelcomeMessageRule> {
        return client.post(
            "$API_WELCOME_MESSAGES/rules/new.json",
            Response.serializer(WelcomeMessageRuleResponse.serializer())
        ) {
            contentType(ContentType.Application.Json)
            body = WelcomeMessageRuleRequest(WelcomeMessageRuleRequest.WelcomeMessageRule(welcomeMessageId))
        }.convertData(WelcomeMessageRuleResponse::welcome_message_rule)
    }

    override suspend fun update(
        id: String,
        text: String,
        mediaId: MediaId?,
        quickReply: QuickReply?
    ): Response<WelcomeMessageData> {
        val attachment =
            mediaId?.let { MessageDataRequest.Attachment("media", MessageDataRequest.Attachment.Media(id = it)) }
        val messageDataRequest = MessageDataRequest(text, attachment, quickReply)
        return client.put("$API_WELCOME_MESSAGES/update.json", Response.serializer(WelcomeMessageData.serializer())) {
            parameter("id", id)
            contentType(ContentType.Application.Json)
            body = WelcomeMessageRequest.WelcomeMessage("", messageDataRequest)
        }
    }
}
