/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.Entities
import kotlinx.serialization.Serializable

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

    @AndroidParcelize
    @Serializable
    public data class Attachment(val type: String, val media: Entities.Media) : AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class QuickReplyResponse(val type: QuickReply.Type, val metadata: String) :
        AndroidParcelable, JvmSerializable
}

@AndroidParcelize
@Serializable
public data class CallToAction(
    val type: String,
    val label: String,
    val url: String
) : AndroidParcelable, JvmSerializable
