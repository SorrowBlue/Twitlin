package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.client.Oauth2Client
import com.sorrowblue.twitlin.api.v2.oauth2.impl.OAuth2ApiImpl
import kotlin.random.Random
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json

@ExperimentalCoroutinesApi
class Oauth2ApiTest {

    @Ignore
    @Test
    fun authorizeTest() {
        val possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~"
        val state = (1..32).joinToString("") { possible[Random.nextInt(possible.length)].toString() }
        val codeChallenge = CodeChallenge.SHA256()
        val url = OAuth2ApiImpl(Oauth2Client("MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ"))
            .authorize("https://maitter.sorrowblue.com", OAuth2Scopes.all, state, codeChallenge)
        println("state: $state")
        println("codeVerifier: ${codeChallenge.codeVerifier}")
        println("url: $url")
    }

    @Ignore
    @Test
    fun tokenTest() = runTest {
        val api = OAuth2ApiImpl(Oauth2Client("MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ"))
        val codeVerifier =
            "b0X7WpT8t-1KFaohFzQnP3Xt-hewQFjAnwd~HcMrfaDXO76yaIA6ovcXvNisCKw.yM6f_0R9tm6rxT~4WdDdGUmyS6PBam8Wnq~FClWKq2Hjro0Mc8.vJHCPw26EFzmX"
        val code = "cy1Pb0lSUnJYRWRpV3pLdktieFJTNGF1eTR3UFYwQnA3WTdwSm9CNzVhZ25TOjE2MzE2NzIxMzQxNDQ6MToxOmFjOjE"
        val token = api.token("https://maitter.sorrowblue.com", code, codeVerifier)
        println(token)
    }

    @Test
    fun codeChallengeTest() {
        val codeVerifier =
            "3TmokCOJaOfFMoxKY~V5pLAbS~1elpZDY43T2MDRrJ2tdXoVxhgUFlfqmIuxMFGF5zM.qZgelgHOseQlBV58gA1UUfPonXe7328YHY_gkEIFxhFjtPdO5AauL7OAF558"
        val codeChallenge = sha256(codeVerifier.encodeToByteArray()).encodeBase64URL()
        assertEquals("gJ_-AywPzaoTYD2gekuTKl_71DaY8yEC4inw5mEtZIE", codeChallenge)
    }

    @Test
    fun oAuth2ResponseErrorTest() {
        val json =
            """{"error":"invalid_request","error_description":"Value passed for the authorization code was invalid."}"""
        val res = Json.decodeFromString(OAuth2Response.serializer(), json)
        assertTrue(res is OAuth2Error)
    }

    @Test
    fun oAuth2ResponseSuccessTest() {
        val json =
            """{"token_type":"bearer","expires_in":7200,"access_token":"xLM0d6n5LoBAgr6mLIV2dxJDaaRSto2XKrt4VbFanIbm15kUl8uwOpxkp8tAdEDvFJ4QaTvyRietKORPVkaIBp25aU5","scope":"offline.access account.follows.write users.read account.follows.read tweet.read","refresh_token":"204erGhYELbRAtbAM0WnRYLieWv4cFjhbRRvabgBdaegMuVdcc4rmhW3ebfWPmPwAykLpm03tOvXxnnMtI0NfHRkjS6"}"""
        val res = Json.decodeFromString(OAuth2Response.serializer(), json)
        assertTrue(res is OAuth2Token)
    }
}
