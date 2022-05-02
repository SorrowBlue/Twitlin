package com.sorrowblue.twitlin.api.client

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Error
 *
 * @property message
 * @property code
 * @constructor Create empty Error
 */
@AndroidParcelize
@Serializable
public data class Error(val message: String, val code: Int) : AndroidParcelable, JvmSerializable
