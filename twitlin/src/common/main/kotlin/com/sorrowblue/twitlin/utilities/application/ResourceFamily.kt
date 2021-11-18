package com.sorrowblue.twitlin.utilities.application

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 */
@Serializable
public enum class ResourceFamily(internal val value: String) {
    @SerialName("lists")
    LISTS("lists"),

    @SerialName("application")
    APPLICATION("application"),

    @SerialName("mutes")
    MUTES("mutes"),

    @SerialName(":version")
    VERSION(":version"),

    @SerialName("guest")
    GUEST("guest"),

    @SerialName("admin_users")
    ADMIN_USERS("admin_users"),

    @SerialName("live_video_stream")
    LIVE_VIDEO_STREAM("live_video_stream"),

    @SerialName("friendships")
    FRIENDSHIPS("friendships"),

    @SerialName("guide")
    GUIDE("guide"),

    @SerialName("auth")
    AUTH("auth"),

    @SerialName("blocks")
    BLOCKS("blocks"),

    @SerialName("geo")
    GEO("geo"),

    @SerialName("promoted_content")
    PROMOTED_CONTENT("promoted_content"),

    @SerialName("users")
    USERS("users"),

    @SerialName("teams")
    TEAMS("teams"),

    @SerialName("followers")
    FOLLOWERS("followers"),

    @SerialName("collections")
    COLLECTIONS("collections"),

    @SerialName("statuses")
    STATUSES("statuses"),

    @SerialName("custom_profiles")
    CUSTOM_PROFILES("custom_profiles"),

    @SerialName("webhooks")
    WEBHOOKS("webhooks"),

    @SerialName("contacts")
    CONTACTS("contacts"),

    @SerialName("labs")
    LABS("labs"),

    @SerialName("i")
    I("i"),

    @SerialName("tweet_prompts")
    TWEET_PROMPTS("tweet_prompts"),

    @SerialName("moments")
    MOMENTS("moments"),

    @SerialName("limiter_scalding_report_creation")
    LIMITER_SCALDING_REPORT_CREATION("limiter_scalding_report_creation"),

    @SerialName("fleets")
    FLEETS("fleets"),

    @SerialName("help")
    HELP("help"),

    @SerialName("feedback")
    FEEDBACK("feedback"),

    @SerialName("business_experience")
    BUSINESS_EXPERIENCE("business_experience"),

    @SerialName("graphql&POST")
    GRAPHQL_POST("graphql&POST"),

    @SerialName("friends")
    FRIENDS("friends"),

    @SerialName("sandbox")
    SANDBOX("sandbox"),

    @SerialName("drafts")
    DRAFTS("drafts"),

    @SerialName("direct_messages")
    DIRECT_MESSAGES("direct_messages"),

    @SerialName("media")
    MEDIA("media"),

    @SerialName("traffic")
    TRAFFIC("traffic"),

    @SerialName("strato")
    STRATO("strato"),

    @SerialName("account_activity")
    ACCOUNT_ACTIVITY("account_activity"),

    @SerialName("account")
    ACCOUNT("account"),

    @SerialName("safert")
    SAFERT("safert"),

    @SerialName("favorites")
    FAVORITES("favorites"),

    @SerialName("device")
    DEVICE("device"),

    @SerialName("tweets")
    TWEETS("tweets"),

    @SerialName("saved_searches")
    SAVED_SEARCHES("saved_searches"),

    @SerialName("oauth")
    OAUTH("oauth"),

    @SerialName("search")
    SEARCH("search"),

    @SerialName("trends")
    TRENDS("trends"),

    @SerialName("live_pipeline")
    LIVE_PIPELINE("live_pipeline"),

    @SerialName("graphql")
    GRAPHQL("graphql"),

    @SerialName("safety")
    SAFETY("safety")
}
