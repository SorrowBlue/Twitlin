package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Direct message
 *
 * @property event
 */
@AndroidParcelize
@Serializable
public data class DirectMessage(val event: DirectMessageEvent) : AndroidParcelable, JvmSerializable
