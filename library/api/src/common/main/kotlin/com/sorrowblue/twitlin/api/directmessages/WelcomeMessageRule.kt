package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class WelcomeMessageRule(
    val id: String,
    @SerialName("created_timestamp")
    internal val _createdTimestamp: String,
    @SerialName("welcome_message_id")
    val welcome_message_id: String
) : AndroidParcelable, JvmSerializable {

    val createdTimestamp: LocalDateTime get() = _createdTimestamp.epochToLocalDateTime()
}

@AndroidParcelize
@Serializable
public data class PagingWelcomeMessageRule(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    @SerialName("welcome_message_rules")
    val welcomeMessageRules: List<WelcomeMessageRule> = emptyList()
) : AndroidParcelable, JvmSerializable
