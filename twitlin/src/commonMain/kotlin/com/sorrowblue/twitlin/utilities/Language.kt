/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property code
 * @property name
 * @property localName
 * @property status
 * @property debug
 */
@Serializable
public data class Language(
    val code: String,
    val name: String,
    @SerialName("local_name") val localName: String,
    val status: String,
    val debug: Boolean
) : JvmSerializable
