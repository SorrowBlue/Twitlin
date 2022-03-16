package com.sorrowblue.twitlin.api.v2.client

import kotlin.test.Test
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProblemSerializerTest {

    @Test
    fun testProblemType() {
        val json = """
            {
                "type": "about:blank"
            }
        """.trimIndent()
        println(Json.decodeFromString<TestProblem>(json))
        println(Json.encodeToString(TestProblem(ProblemType.RESOURCE_NOT_FOUND)))
    }

    @Test
    fun test1() {
        val json = """
            [{"resource_type":"tweet","field":"non_public_metrics.impression_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'non_public_metrics.impression_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"non_public_metrics.url_link_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'non_public_metrics.url_link_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"non_public_metrics.user_profile_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'non_public_metrics.user_profile_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.impression_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.impression_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.like_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.like_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.reply_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.reply_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.retweet_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.retweet_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.url_link_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.url_link_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"organic_metrics.user_profile_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'organic_metrics.user_profile_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.impression_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.impression_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.like_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.like_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.reply_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.reply_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.retweet_count","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.retweet_count' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.url_link_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.url_link_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"},{"resource_type":"tweet","field":"promoted_metrics.user_profile_clicks","parameter":"id","resource_id":"1446829727184941056","title":"Field Authorization Error","section":"data","detail":"Sorry, you are not authorized to access 'promoted_metrics.user_profile_clicks' on the Tweet with id : [1446829727184941056].","value":"1446829727184941056","type":"https://api.twitter.com/2/problems/not-authorized-for-field"}]
        """.trimIndent()
        println(Json.decodeFromString<List<Problem>>(json))
    }
    @Test
    fun errorTest() {
        val json = """
            {"title":"Forbidden","type":"about:blank","detail":"Forbidden","status": "400"}
        """.trimIndent()
        val error = Json.decodeFromString<Problem>(json)
        print(error)
    }
}

@Serializable
data class TestProblem(val type: ProblemType)
