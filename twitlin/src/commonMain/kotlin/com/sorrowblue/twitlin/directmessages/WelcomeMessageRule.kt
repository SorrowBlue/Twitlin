/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStrEpochSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WelcomeMessageRule(
    val id: String,
    @SerialName("created_timestamp")
    @Serializable(LocalDateTimeStrEpochSerializer::class)
    val createdTimestamp: LocalDateTime,
    @SerialName("welcome_message_id")
    val welcome_message_id: String
)

@Serializable
public data class PagingWelcomeMessageRule(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    @SerialName("welcome_message_rules")
    val welcomeMessageRules: List<WelcomeMessageRule>
)
