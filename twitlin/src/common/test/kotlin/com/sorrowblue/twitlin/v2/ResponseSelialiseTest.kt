/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.tweets.PagingData
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ResponseSelialiseTest {

    @Test
    fun test() {
        val body = """
            {
              "data": [
                {
                  "id": "1346889436626259968",
                  "text": "Learn how to use the"
                }
              ],
              "meta": {
                "oldest_id": "1338971066773905408",
                "newest_id": "1346889436626259968",
                "result_count": 10,
                "next_token": "7140dibdnow9c7btw3w29pbzpyo2i2z115csy9le6w56g"
              },
              "errors": [
                {
                  "detail": "Could not find tweet with ids: [1276230436478386177].",
                  "title": "Not Found Error",
                  "resource_type": "tweet",
                  "parameter": "ids",
                  "value": "1276230436478386177",
                  "type": "https://api.twitter.com/2/problems/resource-not-found"
                }
              ]
            }
        """.trimIndent()
        val result = Json.decodeFromString(Response.serializer(PagingData.serializer(Tweet.serializer())), body)
        print(result)
    }
}
