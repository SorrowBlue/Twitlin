package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Cash tag
 *
 * @property start
 * @property end
 * @property tag
 * @constructor Create empty Cash tag
 */
@AndroidParcelize
@Serializable
public data class CashTag(
    override val start: Int,
    override val end: Int,
    val tag: String,
) : TextEntity(), AndroidParcelable, JvmSerializable
