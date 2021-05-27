package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import com.sorrowblue.twitlin.v2.client.Includes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PagingData<T>(
    val data: List<T>,
    val includes: Includes? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) : JvmSerializable {
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
    ) : JvmSerializable
}
