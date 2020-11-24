package com.sorrowblue.twitlin.objects

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

enum class ResourceName {
    LISTS,
    APPLICATION,
    MUTES,
    ADMIN_USERS,
    LIVE_VIDEO_STREAM,
    FRIENDSHIPS,
    GUIDE,
    AUTH,
    BLOCKS,
    GEO,
    USERS,
    TEAMS,
    FOLLOWERS,
    COLLECTIONS,
    STATUSES,
    CUSTOM_PROFILES,
    WEBHOOKS,
    CONTACTS,
    LABS,
    I,
    TWEET_PROMPTS,
    MOMENTS,
    LIMITER_SCALDING_REPORT_CREATION,
    FLEETS,
    HELP,
    FEEDBACK,
    BUSINESS_EXPERIENCE,
    GRAPHQL_POST,
    FRIENDS,
    SANDBOX,
    DRAFTS,
    DIRECT_MESSAGES,
    MEDIA,
    TRAFFIC,
    STRATO,
    ACCOUNT_ACTIVITY,
    ACCOUNT,
    SAFETY,
    FAVORITES,
    DEVICE,
    TWEETS,
    SAVED_SEARCHES,
    OAUTH,
    SEARCH,
    TRENDS,
    LIVE_PIPELINE,
    GRAPHQL
}

data class RateLimitStatus(
    val accessToken: String,
    val resources: List<Resource>
) {

    data class Resource(
        val name: ResourceName,
        val infoList: List<Info>
    ) {

        data class Info(
            val path: String,
            val limit: Int,
            val remaining: Int,
            val reset: LocalDateTime,
        )
    }
}

@Serializable
internal class RateLimitStatusResponse(
    val rate_limit_context: Context,
    val resources: Resources
) {

    @Serializable
    class Context(
        val access_token: String
    )

    @Serializable
    class Resources(
        val lists: JsonObject? = null,
        val application: JsonObject? = null,
        val mutes: JsonObject? = null,
        val admin_users: JsonObject? = null,
        val live_video_stream: JsonObject? = null,
        val friendships: JsonObject? = null,
        val guide: JsonObject? = null,
        val auth: JsonObject? = null,
        val blocks: JsonObject? = null,
        val geo: JsonObject? = null,
        val users: JsonObject? = null,
        val teams: JsonObject? = null,
        val followers: JsonObject? = null,
        val collections: JsonObject? = null,
        val statuses: JsonObject? = null,
        val custom_profiles: JsonObject? = null,
        val webhooks: JsonObject? = null,
        val contacts: JsonObject? = null,
        val labs: JsonObject? = null,
        val i: JsonObject? = null,
        val tweet_prompts: JsonObject? = null,
        val moments: JsonObject? = null,
        val limiter_scalding_report_creation: JsonObject? = null,
        val fleets: JsonObject? = null,
        val help: JsonObject? = null,
        val feedback: JsonObject? = null,
        val business_experience: JsonObject? = null,
        @SerialName("graphql&POST")
        val graphql_POST: JsonObject? = null,
        val friends: JsonObject? = null,
        val sandbox: JsonObject? = null,
        val drafts: JsonObject? = null,
        val direct_messages: JsonObject? = null,
        val media: JsonObject? = null,
        val traffic: JsonObject? = null,
        val strato: JsonObject? = null,
        val account_activity: JsonObject? = null,
        val account: JsonObject? = null,
        val safety: JsonObject? = null,
        val favorites: JsonObject? = null,
        val device: JsonObject? = null,
        val tweets: JsonObject? = null,
        val saved_searches: JsonObject? = null,
        val oauth: JsonObject? = null,
        val search: JsonObject? = null,
        val trends: JsonObject? = null,
        val live_pipeline: JsonObject? = null,
        val graphql: JsonObject? = null,
    )

}
