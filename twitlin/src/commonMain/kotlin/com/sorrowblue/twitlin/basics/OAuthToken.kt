package com.sorrowblue.twitlin.basics

internal data class OAuthToken(
	val oauthToken: String,
	val oauthTokenSecret: String,
	val oauthCallbackConfirmed: Boolean
) {
	companion object {
		fun fromString(string: String): OAuthToken {
			val data =
				string.split("&").map {
					val params = it.split("=")
					params.getOrNull(0) to params.getOrNull(1)
				}.toMap()
			return OAuthToken(
				data["oauth_token"].orEmpty(),
				data["oauth_token_secret"].orEmpty(),
				data["oauth_callback_confirmed"] == "true"
			)
		}
	}
}
