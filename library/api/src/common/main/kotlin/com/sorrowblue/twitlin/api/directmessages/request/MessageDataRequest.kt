package com.sorrowblue.twitlin.api.directmessages.request

import com.sorrowblue.twitlin.api.directmessages.CallToAction
import com.sorrowblue.twitlin.api.directmessages.QuickReply
import com.sorrowblue.twitlin.core.objects.MediaId
import kotlinx.serialization.Serializable

@Serializable
internal class MessageDataRequest(
    val text: String,
    val attachment: Attachment? = null,
    val quick_reply: QuickReply? = null,
    val cta: List<CallToAction>? = null
) {

    @Serializable
    class Attachment(val type: String, val media: Media) {

        @Serializable
        class Media(val id: MediaId)
    }
}
