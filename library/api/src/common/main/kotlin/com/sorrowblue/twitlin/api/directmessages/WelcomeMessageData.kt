package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class WelcomeMessageData(
    @SerialName("welcome_message")
    val welcomeMessage: WelcomeMessage,
    val apps: Map<String, DirectMessageApp>
) : AndroidParcelable, JvmSerializable
