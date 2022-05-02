package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.api.v2.client.Includes
import com.sorrowblue.twitlin.api.v2.client.Problem
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Paging data
 *
 * @param T
 * @property data
 * @property includes
 * @property meta
 * @property errors
 * @constructor Create empty Paging data
 */
@Serializable
public data class PagingData<T>(
    val data: List<T> = emptyList(),
    val includes: Includes = Includes(),
    val meta: Meta,
    val errors: List<Problem>? = null
) {

    /**
     * Meta
     *
     * @property oldestId
     * @property newestId
     * @property resultCount
     * @property nextToken
     * @property previousToken
     * @constructor Create empty Meta
     */
    @Serializable
    public data class Meta(
        @SerialName("oldest_id")
        val oldestId: String? = null,
        @SerialName("newest_id")
        val newestId: String? = null,
        @SerialName("result_count")
        val resultCount: Int,
        @SerialName("next_token")
        val nextToken: String? = null,
        @SerialName("previous_token")
        val previousToken: String? = null
    )
}
