package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Call to action
 *
 * @property type
 * @property label
 * @property url
 */
@AndroidParcelize
@Serializable
public data class CallToAction(
    val type: String,
    val label: String,
    val url: String
) : AndroidParcelable, JvmSerializable
