package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Mention
 *
 * @property start
 * @property end
 * @property username
 * @constructor Create empty Mention
 */
@AndroidParcelize
@Serializable
public data class Mention(
    override val start: Int,
    override val end: Int,
    val username: String,
) : TextEntity(), AndroidParcelable, JvmSerializable
