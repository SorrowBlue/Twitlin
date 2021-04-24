/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property data
 * @property meta
 */
@AndroidParcelize
@Serializable
public data class SearchStreamRules(val data: List<SearchStreamRule> = emptyList(), val meta: Meta) : AndroidParcelable,
    JvmSerializable {

    /**
     * TODO
     *
     * @property sent
     */
    @AndroidParcelize
    @Serializable
    public data class Meta(val _sent: String) : AndroidParcelable, JvmSerializable {

        val sent: LocalDateTime get() = _sent.isoToLocalDateTime()
    }
}
