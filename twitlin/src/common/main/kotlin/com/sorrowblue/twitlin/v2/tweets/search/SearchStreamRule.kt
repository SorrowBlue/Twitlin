package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Search stream rule
 *
 * @property value
 * @property tag
 * @property id
 * @constructor Create empty Search stream rule
 */
@AndroidParcelize
@Serializable
public data class SearchStreamRule internal constructor(
    val value: String,
    val tag: String? = null,
    val id: RuleId
) : AndroidParcelable, JvmSerializable {

    public constructor(value: String, tag: String) : this(value, tag, RuleId(""))
}
