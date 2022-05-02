package com.sorrowblue.twitlin.api.help

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
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
 */
@AndroidParcelize
@Serializable
public data class Language(
    val code: String,
    val name: String,
    @SerialName("local_name")
    val localName: String,
    val status: String,
    val debug: Boolean
) : AndroidParcelable, JvmSerializable
