package com.sorrowblue.twitlin

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.settings.Settings
import com.sorrowblue.twitlin.utils.TweetUtil
import io.ktor.http.HttpMethod
import io.ktor.util.InternalAPI
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.URLEncoder
import java.nio.charset.Charset
import kotlin.test.assertEquals

private const val ACCESS_TOKEN = "938122027231150081-edSNSs0q0D9ahF9VW3zAUushpIbhrxz"
private const val ACCESS_TOKEN_SECRET = "V5e6HQ7zfltRkghgR1B0jBeq4bHHmq0VDfNo5ZT32Otll"

class AuthTest {
	init {
		Napier.base(DebugAntilog())
		Twitlin.initialize(
			"ctNGOKkamPkXfFIcf4iQF37b7",
			"BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T",
			Settings()/*,
			ACCESS_TOKEN,
			ACCESS_TOKEN_SECRET
*/		)
	}

	@Test
	fun authorizeTest() {
		runBlocking {
			Twitlin.Api.oauth.authenticate("snsmate.sorrowblue.com/resones")
//			Twitlin.Api.oauth.authenticate("snsmate://snsmate.sorrowblue.com/resones")
		}.onError {
			Napier.e(it.joinToString(", ") { "${it.code} -> ${it.message}" }, tag = "authorizeTest")
		}.onSuccess {
			Napier.d("AuthenticateUrl = $it" , tag = "authorizeTest")
		}
	}

	@Test
	fun authenticateTest() {
		runBlocking {
			Twitlin.Api.oauth.authenticate("https://snsmate.sorrowblue.com")
		}.onError {
			Napier.e(it.joinToString(", ") { "${it.code} -> ${it.message}" })
		}.onSuccess {
			Napier.d(it)
		}
	}

	@Test
	fun homeTimelineTest() {
		runBlocking {
			Twitlin.Api.statuses.userTimeline(screenName = "sorrowblue_sb", count = 3)
		}.onError {
			println("ERROR: " + it.joinToString(", ") { "${it.code} -> ${it.message}" })
		}.getOrNull()?.forEach {
			if (it.retweetedStatus != null) {
				"""
					リツイート
					============
					$it
					============
					${it.user?.name} さんがリツイート
					${tweet(it.retweetedStatus!!)}
				""".trimIndent()
			} else if (it.quotedStatus != null) {
				"""
					引用リツイート
					============
					$it
					============
					${tweet(it)}
					${tweet(it.quotedStatus!!)}
				""".trimIndent()
			} else {
				"""
					ツイート
					============
					$it
					============
					${tweet(it)}
				""".trimIndent()
			}.also {
				Napier.i(it)
			}
		}
	}

	fun tweet(tweet: TwitterTweet): String {
		val userName = tweet.user?.name
		val screenName = tweet.user?.screenName
		val createAt = tweet.createdAt.format("MM/dd hh:mm:ss")
		return """
			$userName @ $screenName $createAt
			${tweet.text}
		""".trimIndent() + when {
			tweet.extendedEntities?.media?.firstOrNull()?.videoInfo != null -> showVideo(tweet)
			tweet.extendedEntities?.media?.firstOrNull()?.type == Entities.Media.MediaType.PHOTO -> showPhoto(
				tweet
			)
			tweet.extendedEntities?.media?.firstOrNull()?.type == Entities.Media.MediaType.ANIMATED_GIF -> showGif(
				tweet
			)
			else -> ""
		}
	}

	fun showVideo(tweet: TwitterTweet): String? {
		if (tweet.extendedEntities?.media?.firstOrNull()?.type != Entities.Media.MediaType.VIDEO) return null
		val video =
			tweet.extendedEntities?.media?.firstOrNull()?.videoInfo ?: throw Exception("動画じゃない")
		return """
			長さ : ${video.durationMillis}
			アスペクト比 : ${video.aspectRatio.joinToString("/")}
			url : ${video.variants.first().url}
			bitrate : ${video.variants.first().bitrate}
			contentType : ${video.variants.first().contentType}
			thumb = ${tweet.extendedEntities?.media?.first()?.toThumb()}
		""".trimIndent()

	}

	fun showPhoto(tweet: TwitterTweet): String? {
		if (tweet.extendedEntities?.media?.firstOrNull()?.type != Entities.Media.MediaType.PHOTO) return null
		return tweet.extendedEntities!!.media.map {
			"""
				url = ${it.mediaUrlHttps}
				thumb = ${it.toThumb()}
			""".trimIndent()
		}.joinToString("\n")
	}

	fun showGif(tweet: TwitterTweet): String? {
		if (tweet.extendedEntities?.media?.firstOrNull()?.type != Entities.Media.MediaType.ANIMATED_GIF) return null
		val gif =
			tweet.extendedEntities?.media?.firstOrNull()?.videoInfo ?: throw Exception("動画じゃない")
		return """
			url = ${gif.variants.first().url}
			contentType = ${gif.variants.first().contentType}
			thumb = ${tweet.extendedEntities?.media?.first()?.toThumb()}
		""".trimIndent()
	}


	@Test
	fun cardTitle() {
		runBlocking {
			TweetUtil.twitterCard("https://androiddagashi.github.io/")
		}?.let {
			println(it)
		} ?: println("null")
	}

	@OptIn(InternalAPI::class)
	@Test
	fun urlEncodeTest() {
		println("Ladies + Gentlemen".urlEncode() + " = " + "Ladies%20%2B%20Gentlemen")
		assertEquals("Ladies + Gentlemen".urlEncode(), "Ladies%20%2B%20Gentlemen")
		println("An encoded string!".urlEncode() + " = " + "An%20encoded%20string%21")
		assertEquals("An encoded string!".urlEncode(), "An%20encoded%20string%21")
		println("Dogs, Cats & Mice".urlEncode() + " = " + "Dogs%2C%20Cats%20%26%20Mice")
		assertEquals("Dogs, Cats & Mice".urlEncode(), "Dogs%2C%20Cats%20%26%20Mice")
		println("☃".urlEncode() + " = " + "%E2%98%83")
		assertEquals("☃".urlEncode(), "%E2%98%83")
		println("#$%'()=~|`{*}<>?_[;:]./-^\\".urlEncode() + " = " + "%23%24%25%27%28%29%3D~%7C%60%7B*%7D%3C%3E%3F_%5B%3B%3A%5D.%2F-%5E%5C")
		assertEquals(
			"#$%'()=~|`{*}<>?_[;:]./-^\\".urlEncode(),
			"%23%24%25%27%28%29%3D~%7C%60%7B*%7D%3C%3E%3F_%5B%3B%3A%5D.%2F-%5E%5C"
		)
	}

	private fun String.urlEncode() = URLEncoder.encode(this, Charset.forName("UTF-8"))
		.replace("+", "%20")
		.replace("!", "%21")
		.replace(",", "%2C")
		.replace("&", "%26")

	@Test
	fun sogTest() {
		Twitlin.client.oAuthHeader(
			HttpMethod.Post, "https://api.twitter.com/oauth/request_token", listOf(
				"oauth_callback" to "http://myapp.com/twitter/process_callback"
			)
		).let {
			Napier.d("header $it")
		}
	}


}

data class TwitterCard(
	val title: String,
	val url: String,
	val description: String,
	val image: String,
	val card: Card,
	val site: String
) {
	enum class Card {
		SUMMARY,
		SUMMARY_LARGE_IMAGE,
		APP,
		PLAYER,
		UNDEFINED;

		companion object {
			fun valueCam(str: String): Card =
				when (str) {
					"summary" -> SUMMARY
					"summary_large_image" -> SUMMARY_LARGE_IMAGE
					"app" -> APP
					"player" -> PLAYER
					else -> UNDEFINED
				}
		}
	}
}

private fun String.trimExtension() =
	kotlin.runCatching { substring(0, lastIndexOf(".")) }.getOrElse { "" }

private val String.extension
	get() = kotlin.runCatching { substring(lastIndexOf(".") + 1) }.getOrElse { "" }

fun String.toThumb() = trimExtension() + "?format=${extension}&name=thumb"
fun Entities.Media.toThumb() =
	when {
		sizes.thumb.w > 0 -> mediaUrlHttps.trimExtension() + "?format=${mediaUrlHttps.extension}&name=thumb"
		sizes.small.w > 0 -> mediaUrlHttps.trimExtension() + "?format=${mediaUrlHttps.extension}&name=small"
		sizes.medium.w > 0 -> mediaUrlHttps.trimExtension() + "?format=${mediaUrlHttps.extension}&name=medium"
		sizes.large.w > 0 -> expandedUrl.trimExtension() + "?format=${mediaUrlHttps.extension}&name=large"
		else -> mediaUrlHttps
	}
