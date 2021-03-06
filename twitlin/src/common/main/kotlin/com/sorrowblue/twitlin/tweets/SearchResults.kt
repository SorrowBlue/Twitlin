/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.Tweet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property statuses
 * @property searchMetadata
 */
@AndroidParcelize
@Serializable
public data class SearchResults(
    val statuses: List<Tweet>,
    @SerialName("search_metadata")
    val searchMetadata: MetaData
) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property completedIn
     * @property maxId
     * @property maxIdStr
     * @property nextResults
     * @property query
     * @property refreshUrl
     * @property count
     * @property sinceId
     * @property sinceIdStr
     */
    @AndroidParcelize
    @Serializable
    public data class MetaData(
        @SerialName("completed_in")
        val completedIn: Float,
        @SerialName("max_id")
        val maxId: Long,
        @SerialName("max_id_str")
        val maxIdStr: String,
        @SerialName("next_results")
        val nextResults: String,
        val query: String,
        @SerialName("refresh_url")
        val refreshUrl: String,
        val count: Int,
        @SerialName("since_id")
        val sinceId: Long,
        @SerialName("since_id_str")
        val sinceIdStr: String,
    ) : AndroidParcelable, JvmSerializable
}
