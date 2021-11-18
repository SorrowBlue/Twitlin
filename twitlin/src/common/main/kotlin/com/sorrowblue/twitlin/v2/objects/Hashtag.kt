package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Hashtag
 *
 * @property start
 * @property end
 * @property tag
 * @constructor Create empty Hashtag
 */
@AndroidParcelize
@Serializable
public data class Hashtag(
    override val start: Int,
    override val end: Int,
    val tag: String,
) : TextEntity(), AndroidParcelable, JvmSerializable

@Serializable
public sealed class TextEntity {
    public abstract val start: Int
    public abstract val end: Int
}

