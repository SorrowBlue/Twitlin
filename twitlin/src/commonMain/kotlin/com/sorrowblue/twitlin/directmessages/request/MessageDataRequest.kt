/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.request

import com.sorrowblue.twitlin.directmessages.QuickReply
import kotlinx.serialization.Serializable

@Serializable
internal class MessageDataRequest(
    val text: String,
    val attachment: Attachment? = null,
    val quick_reply: QuickReply? = null,
) {

    @Serializable
    class Attachment(val type: String, val media: Media) {

        @Serializable
        class Media(val id: String)
    }
}
