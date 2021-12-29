package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Search stream rules
 *
 * @property data
 * @property meta
 * @constructor Create empty Search stream rules
 */
@AndroidParcelize
@Serializable
public data class SearchStreamRules(
    val data: List<SearchStreamRule> = emptyList(),
    val meta: Meta
) : AndroidParcelable, JvmSerializable {

    /**
     * Meta
     *
     * @property _sent
     * @constructor Create empty Meta
     */
    @AndroidParcelize
    @Serializable
    public data class Meta(@SerialName("sent") val _sent: String) : AndroidParcelable, JvmSerializable {

        val sent: LocalDateTime get() = _sent.isoToLocalDateTime()
    }
}
