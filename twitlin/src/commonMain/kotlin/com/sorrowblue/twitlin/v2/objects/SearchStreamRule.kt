package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.v2.Error
import com.sorrowblue.twitlin.v2.Includes
import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import io.ktor.client.statement.*
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property rules
 * @property sent
 */
data class SearchStreamRule(
    val rules: List<StreamRule>,
    @Serializable(LocalDateTimeSerializer::class)
    val sent: LocalDateTime
) {
    @Serializable
    data class StreamRule(
        val id: String,
        val value: String,
        val tag: String? = null
    )
}

/**
 * TODO
 *
 * @property value
 * @property tag
 */
@Serializable
class AddSearchStreamRule(
    val value: String,
    val tag: String? = null
)

/**
 * TODO
 *
 * @property rules
 * @property sent
 * @property summary
 */
data class AddSearchStreamRuleResult(
    val rules: List<SearchStreamRule.StreamRule>,
    val sent: LocalDateTime,
    val summary: Summary
) {
    @Serializable
    data class Summary(
        val created: Int,
        @SerialName("not_created")
        val notCreated: Int,
        val valid: Int,
        val invalid: Int
    )
}

/**
 * TODO
 *
 * @property sent
 * @property summary
 */
data class DeleteSearchStreamRuleResult(
    val sent: LocalDateTime,
    val summary: Summary
) {
    @Serializable
    data class Summary(
        val deleted: Int,
        @SerialName("not_deleted")
        val notDeleted: Int
    )
}

/**
 * TODO
 *
 * @property add
 */
@Serializable
internal class AddSearchStreamRuleRequest(
    val add: List<AddSearchStreamRule>
)

/**
 * TODO
 *
 * @property delete
 */
@Serializable
internal class DeleteSearchStreamRuleRequest(
    val delete: Delete
) {
    constructor(ids: List<String>) : this(Delete(ids))

    @Serializable
    class Delete(
        val ids: List<String>
    )
}

/**
 * TODO
 *
 * @property data
 * @property meta
 * @property errors
 */
@Serializable
internal class SearchStreamRuleResponse(
    val data: List<SearchStreamRule.StreamRule>? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) {
    fun toSuccess(statusCode: Int) =
        Response.Success(statusCode, SearchStreamRule(data.orEmpty(), meta.sent), errors = errors)

    @Serializable
    class Meta(
        @Serializable(LocalDateTimeSerializer::class)
        val sent: LocalDateTime
    )
}

class SearchRecent(
    val tweets: List<Tweet>,
    val meta: Meta
) {

    @Serializable
    class Meta(
        @SerialName("newest_id")
        val newestId: String? = null,
        @SerialName("oldest_id")
        val oldestId: String? = null,
        @SerialName("result_count")
        val resultCount: Int,
        @SerialName("next_token")
        val nextToken: String? = null
    )
}

@Serializable
internal class SearchRecentResponse(
    val statusCode: Int = 200,
    val data: List<Tweet>? = null,
    val includes: Includes? = null,
    val errors: List<Error>? = null,
    val meta: SearchRecent.Meta
) {
    companion object {
        fun onSuccess(res: SearchRecentResponse, httpResponse: HttpResponse) =
            Response.Success(
                httpResponse.status.value,
                SearchRecent(res.data.orEmpty(), res.meta),
                res.includes,
                res.errors
            )
    }
}


/**
 * TODO
 *
 * @property data
 * @property meta
 * @property errors
 */
@Serializable
internal class AddSearchStreamRuleResponse(
    val data: List<SearchStreamRule.StreamRule>? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) {
    fun toSuccess(statusCode: Int) =
        Response.Success(
            statusCode,
            AddSearchStreamRuleResult(data.orEmpty(), meta.sent, meta.summary),
            errors = errors
        )

    @Serializable
    class Meta(
        @Serializable(LocalDateTimeSerializer::class)
        val sent: LocalDateTime,
        val summary: AddSearchStreamRuleResult.Summary
    )
}

/**
 * TODO
 *
 * @property meta
 * @property errors
 */
@Serializable
internal class DeleteSearchStreamRuleResponse(
    val meta: Meta,
    val errors: List<Error>? = null
) {
    fun toSuccess(statusCode: Int) =
        Response.Success(
            statusCode,
            DeleteSearchStreamRuleResult(meta.sent, meta.summary),
            errors = errors
        )

    @Serializable
    class Meta(
        @Serializable(LocalDateTimeSerializer::class)
        val sent: LocalDateTime,
        val summary: DeleteSearchStreamRuleResult.Summary
    )
}
