/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.objects.Entities
import kotlinx.serialization.Serializable

@Serializable
public data class MessageData(
    val text: String,
    val ctas: List<CallToAction>? = null,
    val entities: Entities = Entities(),
    val attachment: Attachment? = null,
    val quick_reply: QuickReply? = null,
    val quick_reply_response: QuickReplyResponse? = null
) {

    @Serializable
    public data class Attachment(val type: String, val media: Entities.Media)

    @Serializable
    public data class QuickReplyResponse(val type: QuickReply.Type, val metadata: String)
}

@Serializable
public data class CallToAction(
    val type: String,
    val label: String,
    val url: String
)
