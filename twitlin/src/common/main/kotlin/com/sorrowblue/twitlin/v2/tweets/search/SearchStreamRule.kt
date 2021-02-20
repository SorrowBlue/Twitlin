/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property value
 * @property tag
 * @property id
 */
@Serializable
public data class SearchStreamRule internal constructor(
    val value: String,
    val tag: String? = null,
    val id: String
) : JvmSerializable {

    public constructor(value: String, tag: String) : this(value, tag, "")
}
