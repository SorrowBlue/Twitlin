package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.BuildKonfig
import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.client.Oauth2Client
import com.sorrowblue.twitlin.api.v2.oauth2.impl.OAuth2ApiImpl
import com.sorrowblue.twitlin.api.v2.objects.User
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.ktor.http.Url
import kotlin.random.Random
import kotlin.time.ExperimentalTime
import kotlinx.serialization.json.Json

expect fun authenticationFlowScenario(url: String): String?

private fun state(): String {
    val possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~"
    return (1..32).joinToString("") { possible[Random.nextInt(possible.length)].toString() }
}

private val oauth2Client = Oauth2Client(BuildKonfig.CLIENT_ID)

object ProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        super.beforeProject()
//        val api = TwitlinApiV2.getApi<OAuth2Api>(oauth2Client)
//        val codeChallenge = CodeChallenge.SHA256()
//        val url = api.authorize("https://maitter.sorrowblue.com", OAuth2Scopes.values().asList(), state(), codeChallenge)
//        val codeVerifier = codeChallenge.codeVerifier
//        val response = authenticationFlowScenario(url)!!
//        val code = Url(response).parameters.get("code")!!
//        val state = Url(response).parameters.get("state")!!
//        val token = api.token("https://maitter.sorrowblue.com", code, codeVerifier)
//        token.shouldBeInstanceOf<OAuth2Token>()
//        oAuth2Token = token as OAuth2Token
//        me = TwitlinApiV2.getApi<UsersApi>(oAuth2Client).me().dataOrNull()?.data
    }

    var oAuth2Token: OAuth2Token? = null
    var me: User? = null
    val oAuth2Client: Oauth2Client get() = Oauth2Client(BuildKonfig.CLIENT_ID, oAuth2Token!!)
}

@ExperimentalTime
class Oauth2ApiTest : FunSpec({

    test("authorizeTest") {
        val codeChallenge = CodeChallenge.SHA256()
        val api = TwitlinApiV2.getApi<OAuth2Api>(oauth2Client)
        val url = api.authorize(
            "https://maitter.sorrowblue.com",
            OAuth2Scopes.values().asList(),
            "b589fb9ea31c1b1d",
            codeChallenge
        )
        println("codeVerifier: ${codeChallenge.codeVerifier}")
        println("url: $url")
        url shouldNotBe null
    }

    test("tokenTest").config(enabled = false) {
        val codeChallenge = CodeChallenge.SHA256()
        val url = OAuth2ApiImpl(Oauth2Client(BuildKonfig.CLIENT_ID))
            .authorize(
                "https://maitter.sorrowblue.com",
                OAuth2Scopes.values().asList(),
                state(),
                codeChallenge
            )

        val api = OAuth2ApiImpl(Oauth2Client(BuildKonfig.CLIENT_ID))
        val codeVerifier = codeChallenge.codeVerifier
        val res = authenticationFlowScenario(url)!!
        println(res)
        val code = Url(res).parameters["code"]!!
        val token = api.token("https://maitter.sorrowblue.com", code, codeVerifier)
        println(token)
    }

    test("refreshTest").config(enabled = false) {
        val api = OAuth2ApiImpl(Oauth2Client("MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ"))
        val token =
            api.refresh("U0FZSEJXX0JxMVhkQXU1YzdhTnlmQXpCSDdHOFRBenYxdjFaOFFySEJqOHBxOjE2NDg1NTE1OTI3Njk6MTowOnJ0OjE")
        println(token)
    }

    test("codeChallengeTest") {
        val codeVerifier =
            "3TmokCOJaOfFMoxKY~V5pLAbS~1elpZDY43T2MDRrJ2tdXoVxhgUFlfqmIuxMFGF5zM.qZgelgHOseQlBV58gA1UUfPonXe7328YHY_gkEIFxhFjtPdO5AauL7OAF558"
        val codeChallenge = sha256(codeVerifier.encodeToByteArray()).encodeBase64URL()
        codeChallenge shouldBe "gJ_-AywPzaoTYD2gekuTKl_71DaY8yEC4inw5mEtZIE"
    }

    test("oAuth2ResponseErrorTest") {
        val json =
            """{"error":"invalid_request","error_description":"Value passed for the authorization code was invalid."}"""
        val res = Json.decodeFromString(OAuth2Response.serializer(), json)
        res.shouldBeInstanceOf<OAuth2Error>()
    }

    test("oAuth2ResponseSuccessTest") {
        val json =
            """{"token_type":"bearer","expires_in":7200,"access_token":"xLM0d6n5LoBAgr6mLIV2dxJDaaRSto2XKrt4VbFanIbm15kUl8uwOpxkp8tAdEDvFJ4QaTvyRietKORPVkaIBp25aU5","scope":"offline.access","refresh_token":"204erGhYELbRAtbAM0WnRYLieWv4cFjhbRRvabgBdaegMuVdcc4rmhW3ebfWPmPwAykLpm03tOvXxnnMtI0NfHRkjS6"}"""
        val res = Json.decodeFromString(OAuth2Response.serializer(), json)
        res.shouldBeInstanceOf<OAuth2Token>()
    }
})
