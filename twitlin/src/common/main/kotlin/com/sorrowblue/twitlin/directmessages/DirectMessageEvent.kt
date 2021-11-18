package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class DirectMessageEvent(
    val type: String,
    val id: String,
    @SerialName("created_timestamp")
    val _createdTimestamp: String,
    @SerialName("initiated_via")
    val initiatedVia: InitiatedVia? = null,
    @SerialName("message_create")
    val messageCreate: MessageCreate
) : AndroidParcelable, JvmSerializable {

    @KotlinIgnoredOnParcel
    val createdTimestamp: LocalDateTime
        get() = _createdTimestamp.epochToLocalDateTime()

    @AndroidParcelize
    @Serializable
    public data class InitiatedVia(
        @SerialName("tweet_id")
        val tweetId: String? = null,
        @SerialName("welcome_message_id")
        val welcomeMessageId: String? = null
    ) : AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class MessageCreate(
        val target: Target,
        @SerialName("sender_id")
        val senderId: String,
        @SerialName("source_app_id")
        val sourceAppId: String? = null,
        @SerialName("message_data")
        val messageData: MessageData
    ) : AndroidParcelable, JvmSerializable {

        @AndroidParcelize
        @Serializable
        public data class Target(
            @SerialName("recipient_id") val recipientId: String
        ) : AndroidParcelable, JvmSerializable
    }
}
