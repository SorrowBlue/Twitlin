package com.sorrowblue.twitlin.api.client

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Consumer keys
 *
 * @property key
 * @property secret
 */
@AndroidParcelize
@Serializable
public data class ConsumerKeys(val key: String, val secret: String) : AndroidParcelable, JvmSerializable
