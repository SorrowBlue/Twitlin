/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.objects.Entities
import kotlinx.serialization.Serializable

@Serializable
public data class MessageData(
    val text: String,
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
