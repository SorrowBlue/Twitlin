package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Direct message app
 *
 * @property id
 * @property name
 * @property url
 */
@AndroidParcelize
@Serializable
public data class DirectMessageApp(
    val id: String,
    val name: String,
    val url: String
) : AndroidParcelable, JvmSerializable
