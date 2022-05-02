package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Direct message event
 *
 * @property type
 * @property id
 * @property _createdTimestamp
 * @property initiatedVia
 * @property messageCreate
 */
@AndroidParcelize
@Serializable
public data class DirectMessageEvent(
    val type: String,
    val id: String,
    @SerialName("created_timestamp")
    internal val _createdTimestamp: String,
    @SerialName("initiated_via")
    val initiatedVia: InitiatedVia? = null,
    @SerialName("message_create")
    val messageCreate: MessageCreate
) : AndroidParcelable, JvmSerializable {

    /**
     * Created timestamp
     */
    @KotlinIgnoredOnParcel
    val createdTimestamp: LocalDateTime
        get() = _createdTimestamp.epochToLocalDateTime()

    /**
     * Initiated via
     *
     * @property tweetId
     * @property welcomeMessageId
     */
    @AndroidParcelize
    @Serializable
    public data class InitiatedVia(
        @SerialName("tweet_id")
        val tweetId: String? = null,
        @SerialName("welcome_message_id")
        val welcomeMessageId: String? = null
    ) : AndroidParcelable, JvmSerializable

    /**
     * Message create
     *
     * @property target
     * @property senderId
     * @property sourceAppId
     * @property messageData
     */
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

        /**
         * Target
         *
         * @property recipientId
         */
        @AndroidParcelize
        @Serializable
        public data class Target(
            @SerialName("recipient_id")
            val recipientId: String
        ) : AndroidParcelable, JvmSerializable
    }
}
