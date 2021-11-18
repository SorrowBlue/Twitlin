package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
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
