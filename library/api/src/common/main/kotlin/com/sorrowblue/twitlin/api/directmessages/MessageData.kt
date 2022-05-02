package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.api.objects.Entities
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Message data
 *
 * @property text
 * @property ctas
 * @property entities
 * @property attachment
 * @property quick_reply
 * @property quick_reply_response
 */
@AndroidParcelize
@Serializable
public data class MessageData(
    val text: String,
    val ctas: List<CallToAction>? = null,
    val entities: Entities = Entities(),
    val attachment: Attachment? = null,
    val quick_reply: QuickReply? = null,
    val quick_reply_response: QuickReplyResponse? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * Attachment
     *
     * @property type
     * @property media
     */
    @AndroidParcelize
    @Serializable
    public data class Attachment(val type: String, val media: Entities.Media) : AndroidParcelable, JvmSerializable

    /**
     * Quick reply response
     *
     * @property type
     * @property metadata
     */
    @AndroidParcelize
    @Serializable
    public data class QuickReplyResponse(
        val type: QuickReply.Type,
        val metadata: String
    ) :
        AndroidParcelable, JvmSerializable
}
