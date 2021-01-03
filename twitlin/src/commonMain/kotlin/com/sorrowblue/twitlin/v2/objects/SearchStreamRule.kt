/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.Error
import com.sorrowblue.twitlin.v2.Includes
import com.sorrowblue.twitlin.v2.Response
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property rules
 * @property sent
 */
public data class SearchStreamRule(
    val rules: List<StreamRule>,
    @Serializable(LocalDateTimeISOSerializer::class)
    val sent: LocalDateTime
) : JvmSerializable {
    @Serializable
    public data class StreamRule(
        val id: String,
        val value: String,
        val tag: String? = null
    ) : JvmSerializable
}

/**
 * TODO
 *
 * @property value
 * @property tag
 */
@Serializable
public data class AddSearchStreamRule(
    val value: String,
    val tag: String? = null
) : JvmSerializable

/**
 * TODO
 *
 * @property rules
 * @property sent
 * @property summary
 */
public data class AddSearchStreamRuleResult(
    val rules: List<SearchStreamRule.StreamRule>,
    val sent: LocalDateTime,
    val summary: Summary
) : JvmSerializable {

    @Serializable
    public data class Summary(
        val created: Int,
        @SerialName("not_created")
        val notCreated: Int,
        val valid: Int,
        val invalid: Int
    ) : JvmSerializable
}

/**
 * TODO
 *
 * @property sent
 * @property summary
 */
public data class DeleteSearchStreamRuleResult(
    val sent: LocalDateTime,
    val summary: Summary
) : JvmSerializable {
    @Serializable
    public data class Summary(
        val deleted: Int,
        @SerialName("not_deleted")
        val notDeleted: Int
    ) : JvmSerializable
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
        @Serializable(LocalDateTimeISOSerializer::class)
        val sent: LocalDateTime
    )
}

public data class SearchRecent(
    val tweets: List<Tweet>,
    val meta: Meta
) : JvmSerializable {

    @Serializable
    public data class Meta(
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
        @Serializable(LocalDateTimeISOSerializer::class)
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
        @Serializable(LocalDateTimeISOSerializer::class)
        val sent: LocalDateTime,
        val summary: DeleteSearchStreamRuleResult.Summary
    )
}
