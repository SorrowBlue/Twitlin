package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.RateLimitStatus
import com.sorrowblue.twitlin.objects.RateLimitStatus.Resource
import com.sorrowblue.twitlin.objects.RateLimitStatusResponse
import com.sorrowblue.twitlin.objects.ResourceName
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

interface ApplicationApi {
    suspend fun rateLimitStatus(): Response<RateLimitStatus>
}

internal class ApplicationApiImp(private val client: Client) : ApplicationApi {

    override suspend fun rateLimitStatus(): Response<RateLimitStatus> {
        return client.get<RateLimitStatusResponse>("https://api.twitter.com/1.1/application/rate_limit_status.json")
            .fold({
                val res = it.resources
                val resources = mutableListOf<Resource>()
                resources.add(Resource(ResourceName.LISTS, res.lists.infoList))
                resources.add(Resource(ResourceName.APPLICATION, res.application.infoList))
                resources.add(Resource(ResourceName.MUTES, res.mutes.infoList))
                resources.add(Resource(ResourceName.ADMIN_USERS, res.admin_users.infoList))
                resources
                    .add(Resource(ResourceName.LIVE_VIDEO_STREAM, res.live_video_stream.infoList))
                resources.add(Resource(ResourceName.FRIENDSHIPS, res.friendships.infoList))
                resources.add(Resource(ResourceName.GUIDE, res.guide.infoList))
                resources.add(Resource(ResourceName.AUTH, res.auth.infoList))
                resources.add(Resource(ResourceName.BLOCKS, res.blocks.infoList))
                resources.add(Resource(ResourceName.GEO, res.geo.infoList))
                resources.add(Resource(ResourceName.USERS, res.users.infoList))
                resources.add(Resource(ResourceName.TEAMS, res.teams.infoList))
                resources.add(Resource(ResourceName.FOLLOWERS, res.followers.infoList))
                resources.add(Resource(ResourceName.COLLECTIONS, res.collections.infoList))
                resources.add(Resource(ResourceName.STATUSES, res.statuses.infoList))
                resources.add(Resource(ResourceName.CUSTOM_PROFILES, res.custom_profiles.infoList))
                resources.add(Resource(ResourceName.WEBHOOKS, res.webhooks.infoList))
                resources.add(Resource(ResourceName.CONTACTS, res.contacts.infoList))
                resources.add(Resource(ResourceName.LABS, res.labs.infoList))
                resources.add(Resource(ResourceName.I, res.i.infoList))
                resources.add(Resource(ResourceName.TWEET_PROMPTS, res.tweet_prompts.infoList))
                resources.add(Resource(ResourceName.MOMENTS, res.moments.infoList))
                resources.add(
                    Resource(
                        ResourceName.LIMITER_SCALDING_REPORT_CREATION,
                        res.limiter_scalding_report_creation.infoList
                    )
                )
                resources.add(Resource(ResourceName.FLEETS, res.fleets.infoList))
                resources.add(Resource(ResourceName.HELP, res.help.infoList))
                resources.add(Resource(ResourceName.FEEDBACK, res.feedback.infoList))
                resources.add(
                    Resource(
                        ResourceName.BUSINESS_EXPERIENCE,
                        res.business_experience.infoList
                    )
                )
                resources.add(Resource(ResourceName.GRAPHQL_POST, res.graphql_POST.infoList))
                resources.add(Resource(ResourceName.FRIENDS, res.friends.infoList))
                resources.add(Resource(ResourceName.SANDBOX, res.sandbox.infoList))
                resources.add(Resource(ResourceName.DRAFTS, res.drafts.infoList))
                resources.add(Resource(ResourceName.DIRECT_MESSAGES, res.direct_messages.infoList))
                resources.add(Resource(ResourceName.MEDIA, res.media.infoList))
                resources.add(Resource(ResourceName.TRAFFIC, res.traffic.infoList))
                resources.add(Resource(ResourceName.STRATO, res.strato.infoList))
                resources.add(
                    Resource(
                        ResourceName.ACCOUNT_ACTIVITY,
                        res.account_activity.infoList
                    )
                )
                resources.add(Resource(ResourceName.ACCOUNT, res.account.infoList))
                resources.add(Resource(ResourceName.SAFETY, res.safety.infoList))
                resources.add(Resource(ResourceName.FAVORITES, res.favorites.infoList))
                resources.add(Resource(ResourceName.DEVICE, res.device.infoList))
                resources.add(Resource(ResourceName.TWEETS, res.tweets.infoList))
                resources.add(Resource(ResourceName.SAVED_SEARCHES, res.saved_searches.infoList))
                resources.add(Resource(ResourceName.OAUTH, res.oauth.infoList))
                resources.add(Resource(ResourceName.SEARCH, res.search.infoList))
                resources.add(Resource(ResourceName.TRENDS, res.trends.infoList))
                resources.add(Resource(ResourceName.LIVE_PIPELINE, res.live_pipeline.infoList))
                resources.add(Resource(ResourceName.GRAPHQL, res.graphql.infoList))
                Response.success(RateLimitStatus(it.rate_limit_context.access_token, resources))
            }, { Response.error(it) })
    }

    private val JsonObject?.infoList
        get() = this?.map {
            val jsonObject = it.value.jsonObject
            Resource.Info(
                it.key,
                jsonObject.getValue("limit").jsonPrimitive.int,
                jsonObject.getValue("remaining").jsonPrimitive.int,
                jsonObject.getValue("reset").let {
                    Instant.fromEpochSeconds(it.jsonPrimitive.long)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                },
            )
        }.orEmpty()

}
