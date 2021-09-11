package com.sorrowblue.twitlin.core

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.core.AuthenticationUtils.creatingSignatureBaseString
import com.sorrowblue.twitlin.utils.Security
import com.sorrowblue.twitlin.utils.urlEncode
import com.sorrowblue.twitlin.v2.client.ConsumerKeys
import io.ktor.http.HttpMethod
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

public object OauthHeaders {
    public const val PREFIX: String = "oauth_"

    public const val KEY_CONSUMER_KEY: String = "${PREFIX}consumer_key"
    public const val KEY_NONCE: String = "${PREFIX}nonce"
    public const val KEY_SIGNATURE: String = "${PREFIX}_signature"
    public const val KEY_SIGNATURE_METHOD: String = "${PREFIX}signature_method"
    public const val KEY_TIMESTAMP: String = "${PREFIX}timestamp"
    public const val KEY_TOKEN: String = "${PREFIX}token"
    public const val KEY_VERSION: String = "${PREFIX}version"

    public const val VALUE_SIGNATURE_METHOD: String = "HMAC-SHA1"
    public const val VALUE_VERSION: String = "1.0"
}

public object AuthenticationUtils {

    public fun createSignature(
        method: HttpMethod,
        noQueryUrl: String,
        consumerKey: String,
        consumerSecret: String,
        nonce: String,
        timestamp: String,
        accessToken: AccessToken?,
        params: List<Pair<String, String>>
    ): String {
        val parameterString =
            collectingParametersForSignature(consumerKey, nonce, timestamp, accessToken?.oauthToken, params)
        val baseString = creatingSignatureBaseString(method.value, noQueryUrl, parameterString)
        val signingKey = getSigningKey(consumerSecret, accessToken?.oauthTokenSecret)
        return oAuthSignature(baseString, signingKey)
    }

    public fun createSignature(
        method: HttpMethod,
        noQueryUrl: String,
        consumerKeys: ConsumerKeys,
        nonce: String,
        timestamp: String,
        accessToken: AccessToken?,
        params: List<Pair<String, String>>
    ): String {
        val parameterString =
            collectingParametersForSignature(consumerKeys.key, nonce, timestamp, accessToken?.oauthToken, params)
        val baseString = creatingSignatureBaseString(method.value, noQueryUrl, parameterString)
        val signingKey = getSigningKey(consumerKeys.secret, accessToken?.oauthTokenSecret)
        return oAuthSignature(baseString, signingKey)
    }

    /**
     * Collecting parameters
     *
     * Next, gather all of the parameters included in the request.
     * There are two such locations for these additional parameters - the URL (as part of the query string) and the request body.
     *
     * @param consumerKey The [consumerKey] identifies which application is making the request.
     * Obtain this value from the settings page for your [Twitter app](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps)
     * in the [developer portal](https://developer.twitter.com/en/docs/basics/developer-portal/overview).
     * @param nonce The [nonce] parameter is a unique token your application should generate for each unique request.
     * Twitter will use this value to determine whether a request has been submitted multiple times.
     * The value for this request was generated by base64 encoding 32 bytes of random data, and stripping out all non-word characters,
     * but any approach which produces a relatively random alphanumeric string should be OK here.
     * @param timestamp The [timestamp] parameter indicates when the request was created.
     * This value should be the number of seconds since the Unix epoch at the point the request is generated,
     * and should be easily generated in most programming languages.
     * Twitter will reject requests which were created too far in the past,
     * so it is important to keep the clock of the computer generating requests in sync with NTP.
     * @param oauthToken The [oauthToken] parameter typically represents a user’s permission to share access to their account with your application.
     * There are a few authentication requests where this value is not passed or is a different form of token,
     * but those are covered in detail in [Obtaining access tokens](https://developer.twitter.com/en/docs/basics/authentication/oauth-1-0a).
     * For most general-purpose requests, you will use what is referred to as an access token.
     *
     * You can generate a valid [access token](https://developer.twitter.com/en/docs/basics/authentication/oauth-1-0a)
     * for your account on the settings page for your [Twitter app](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps)
     * on the [developer portal](https://developer.twitter.com/en/docs/basics/developer-portal/overview).
     * @param params
     * @return
     */
    public fun collectingParametersForSignature(
        consumerKey: String,
        nonce: String,
        timestamp: String,
        oauthToken: String?,
        params: List<Pair<String, String>>
    ): String {
        return listOf(
            OauthHeaders.KEY_CONSUMER_KEY to consumerKey,
            OauthHeaders.KEY_NONCE to nonce,
            OauthHeaders.KEY_SIGNATURE_METHOD to OauthHeaders.VALUE_SIGNATURE_METHOD,
            OauthHeaders.KEY_TIMESTAMP to timestamp,
            OauthHeaders.KEY_VERSION to OauthHeaders.VALUE_VERSION
        ).plus(params).run { oauthToken?.let { plus(OauthHeaders.KEY_TOKEN to it) } ?: this }
            // 1. Percent encode every key and value that will be signed.
            .map { it.first.urlEncode() to it.second.urlEncode() }
            // 2. Sort the list of parameters alphabetically [1] by encoded key [2].
            .sortedBy { it.first }
            // 3. Append the encoded key to the output string.
            // 4. Append the ‘=’ character to the output string.
            // 5. Append the encoded value to the output string.
            // 6. If there are more key/value pairs remaining, append a ‘&’ character to the output string.
            .joinToString("&") { "${it.first}=${it.second}" }
    }

    /**
     * Collecting parameters
    You should be able to see that the header contains 7 key/value pairs, where the keys all begin with
    the string `oauth_`. For any given Twitter API request, collecting these 7 values and creating a
    similar header will allow you to specify authorization for the request.
     *
     * @param consumerKey The [consumerKey] identifies which application is making the
     * request. Obtain this value from the settings page for your
     * [Twitter app](https://developer.twitter.com/content/developer-twitter/en/docs/basics/developer-portal/guides/apps)
     * in the [developer portal](https://developer.twitter.com/content/developer-twitter/en/docs/basics/developer-portal/overview).
     * @param nonce The [nonce] parameter is a unique token your application should generate
     * for each unique request. Twitter will use this value to determine whether a request has been
     * submitted multiple times. The value for this request was generated by base64 encoding 32 bytes
     * of random data, and stripping out all non-word characters, but any approach which produces a
     * relatively random alphanumeric string should be OK here.
     * @param signature The [signature] parameter contains a value which is generated by
     * running all of the other request parameters and two secret values through a signing algorithm.
     * The purpose of the signature is so that Twitter can verify that the request has not been modified
     * in transit, verify the application sending the request, and verify that the application has
     * authorization to interact with the user’s account.
     *
     * The process for calculating the oauth_signature for this request is described in
     * [calculateSignatureBase64].
     * @param timestamp The [timestamp] parameter indicates when the request was created. This value
     * should be the number of seconds since the Unix epoch at the point the request is generated, and
     * should be easily generated in most programming languages. Twitter will reject requests which were
     * created too far in the past, so it is important to keep the clock of the computer generating
     * requests in sync with NTP.
     * @param oauthToken The [oauthToken] parameter typically represents a user’s permission to share
     * access to their account with your application. There are a few authentication requests where this
     * value is not passed or is a different form of token, but those are covered in detail in
     * [Obtaining access tokens](https://developer.twitter.com/en/docs/basics/authentication/oauth-1-0a).
     * For most general-purpose requests, you will use what is referred to as an **access token**.
     *
     * You can generate a valid [access token](https://developer.twitter.com/en/docs/basics/authentication/oauth-1-0a)
     * for your account on the settings page for your
     * [Twitter app](https://developer.twitter.com/content/developer-twitter/en/docs/basics/developer-portal/guides/apps)
     * on the [developer portal](https://developer.twitter.com/content/developer-twitter/en/docs/basics/developer-portal/overview).
     * @param params TODO
     * @return TODO
     */
    internal fun collectingParameters(
        consumerKey: String,
        nonce: String,
        signature: String,
        timestamp: String,
        oauthToken: String?,
        params: List<Pair<String, String>>
    ): List<Pair<String, String>> {
        return listOf(
            OauthHeaders.KEY_CONSUMER_KEY to consumerKey,
            OauthHeaders.KEY_NONCE to nonce,
            OauthHeaders.KEY_SIGNATURE to signature,
            OauthHeaders.KEY_SIGNATURE_METHOD to OauthHeaders.VALUE_SIGNATURE_METHOD,
            OauthHeaders.KEY_TIMESTAMP to timestamp,
            OauthHeaders.KEY_VERSION to OauthHeaders.VALUE_VERSION,
        ).run { oauthToken?.let { plus(OauthHeaders.KEY_TOKEN to it) } ?: this }
            .plus(params.takeWhile { it.first.startsWith(OauthHeaders.PREFIX) })
            .sortedBy { it.first }
    }

    /**
     * Creating the signature base string
     *
     * The three values collected so far must be joined to make a single string, from which the signature will be generated.
     * This is called the signature base string by the OAuth specification.
     * To encode the HTTP method, base URL, and parameter string into a single string:
     *
     * @param method [HttpMethod.value]で定義されている HTTPリクエストメソッド
     * @param url
     * @param parameterString
     * @return
     */
    public fun creatingSignatureBaseString(method: String, url: String, parameterString: String): String {
        return StringBuilder()
            // 1. Convert the HTTP Method to uppercase and set the output string equal to this value.
            .append(method)
            // 2. Append the ‘&’ character to the output string.
            .append("&")
            // 3. Percent encode the URL and append it to the output string.
            .append(url.urlEncode())
            // 4. Append the ‘&’ character to the output string.
            .append("&")
            // 5. Percent encode the parameter string and append it to the output string.
            .append(parameterString.urlEncode())
            .toString()
    }

    /**
     * Getting a signing key
     *
     * The last pieces of data to collect are secrets which identify
     * the [Twitter app](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps) making the request,
     * and the user the request is on behalf of.
     * It is very important to note that these values are incredibly sensitive and should never be shared with anyone.
     *
     * @param consumerSecret The value which identifies your app to Twitter is called the **consumer secret**
     * and can be found in the [developer portal](https://developer.twitter.com/en/docs/basics/developer-portal/overview)
     * by viewing the [app details page](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps).
     * This will be the same for every request your Twitter app sends.
     * @param oAuthTokenSecret The value which identifies the account your application is acting on behalf of is called the **OAuth token secret**.
     * This value can be obtained in several ways, all of which are described
     * in [obtaining access tokens](https://developer.twitter.com/en/docs/basics/authentication/oauth-1-0a).
     */
    public fun getSigningKey(consumerSecret: String, oAuthTokenSecret: String?): String =
        "$consumerSecret&${oAuthTokenSecret.orEmpty()}"

    /**
     * Finally, the signature is calculated by passing the signature base string and signing key to the
     * HMAC-SHA1 hashing algorithm. The details of the algorithm are explained as hash_hmac function.
     * The output of the HMAC signing function is a binary string. This needs to be base64 encoded to
     * produce the signature string.
     *
     * @param baseString [creatingSignatureBaseString]で取得した文字列
     * @param signingKey [getSigningKey]で取得した署名鍵
     * @return HMAC-SHA1ハッシュアルゴリズムの結果をBase64にした文字列
     */
    @OptIn(InternalAPI::class)
    public fun oAuthSignature(baseString: String, signingKey: String): String =
        calculateSignature(baseString, signingKey).encodeBase64()

    /**
     * Finally, the signature is calculated by passing the signature base string and signing key to the
     * HMAC-SHA1 hashing algorithm. The details of the algorithm are explained as hash_hmac function.
     * The output of the HMAC signing function is a binary string. This needs to be base64 encoded to
     * produce the signature string.
     *
     * @param baseString [creatingSignatureBaseString]で取得した文字列
     * @param signingKey [getSigningKey]で取得した署名鍵
     * @return HMAC-SHA1ハッシュアルゴリズムで処理された結果
     */
    private fun calculateSignature(baseString: String, signingKey: String): ByteArray =
        Security.hmacSHA1(signingKey.encodeToByteArray(), baseString.encodeToByteArray())

    /**
     * To build the header string, imagine writing to a string named DST.
     * 1. Append the string `OAuth ` (including the space at the end) to DST.
     * 1. For each key/value pair of the 7 parameters listed above:
     *     1. [Percent encode](https://developer.twitter.com/en/docs/basics/authentication/guides/percent-encoding-parameters.html)
     *     the key and append it to DST.
     *     1. Append the equals character `=` to DST.
     *     1. Append a double quote `”` to DST.
     *     1. [Percent encode](https://developer.twitter.com/en/docs/basics/authentication/guides/percent-encoding-parameters.html)
     *     the value and append it to DST.
     *     1. Append a double quote `”` to DST.
     *     1. If there are key/value pairs remaining, append a comma `,` and a space ` ` to DST.
     *
     * Pay particular attention to the percent encoding of the values when building this string.
     * For example, the `oauth_signature` value of `tnnArxj06cWHq44gCs1OSKk/jLY=` must be encoded as
     * `tnnArxj06cWHq44gCs1OSKk%2FjLY%3D`.
     *
     * @param params TODO
     * @return TODO
     */
    public fun buildHeaderString(params: List<Pair<String, String>>): String {
        val dst = StringBuilder()
        dst.append("OAuth ")
        params.forEachIndexed { index, it ->
            dst.append(it.first.urlEncode())
            dst.append("=")
            dst.append("\"")
            dst.append(it.second.urlEncode())
            dst.append("\"")
            if (index != params.lastIndex) {
                dst.append(", ")
            }
        }
        return dst.toString()
    }

}
