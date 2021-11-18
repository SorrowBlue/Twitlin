package com.sorrowblue.twitlin.utilities.help

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Language
 *
 * @property code
 * @property name
 * @property localName
 * @property status
 * @property debug
 * @constructor Create empty Language
 */
@AndroidParcelize
@Serializable
public data class Language(
    val code: String,
    val name: String,
    @SerialName("local_name") val localName: String,
    val status: String,
    val debug: Boolean
) : AndroidParcelable, JvmSerializable
