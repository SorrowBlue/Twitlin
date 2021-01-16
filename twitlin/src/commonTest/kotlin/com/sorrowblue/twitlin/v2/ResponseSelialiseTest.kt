/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package v2

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.tweets.PagingTweet
import kotlinx.serialization.decodeFromString
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
                  "text": "Learn how to use the user Tweet timeline and user mention timeline endpoints in the Twitter API v2 to explore Tweets and mentions \uD83D\uDC47\n\nhttps://t.co/8ef0gXruSD"
                },
                {
                  "id": "1346588685555306497",
                  "text": "\uD83D\uDD11\uD83D\uDD11 On Tuesday, January 12th, we’re removing the ability to view existing consumer API keys from the developer portal. Be sure to save your API keys in a secure place before Tuesday to ensure your access to the #TwitterAPI is not disrupted. Learn more https://t.co/UztnVZrVwa"
                },
                {
                  "id": "1341761599976181763",
                  "text": "\uD83D\uDCC8 From Tweets to Google Sheets: @JessicaGarson explains how to use Python to seamlessly turn a #TwitterAPI response into a spreadsheet. https://t.co/PYLaPlmUSU"
                },
                {
                  "id": "1339981239294566401",
                  "text": "Introducing a new Tweet field on v2 endpoints: “reply_settings\" shows how the Tweet author allowed others to reply to their post. This can help you filter only by Tweets you can reply to, or better analyze public conversations. Learn more about it here \uD83D\uDC47 https://t.co/XR09zDvpmZ https://t.co/YIfg7F0En2"
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
        val result = Json.decodeFromString<Response<PagingTweet>>(body)
        print(result)
    }
}
