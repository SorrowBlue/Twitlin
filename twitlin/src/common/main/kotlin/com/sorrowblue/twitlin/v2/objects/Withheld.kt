package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Withheld
 *
 * @property copyright
 * @property countryCodes
 * @constructor Create empty Withheld
 */
@AndroidParcelize
@Serializable
public data class Withheld(
    val copyright: Boolean,
    @SerialName("country_codes")
    val countryCodes: List<String>,
) : AndroidParcelable, JvmSerializable
