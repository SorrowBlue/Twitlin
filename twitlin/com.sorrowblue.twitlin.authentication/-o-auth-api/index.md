//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuthApi](index.md)



# OAuthApi  
 [common] interface [OAuthApi](index.md)

##  OAuth 1.0a  


Many endpoints on the Twitter developer platform use the OAuth 1.0a method to act, or make API requests, on behalf of a Twitter account. For example, if you have a Twitter developer app, you can make API requests on behalf of any Twitter account as long as that user authenticates your app.



Please note: if you are not are not familiar with concepts such as HMAC-SHA1 and percent encoding, we recommend that you check out the "useful tools" section below that lists some API clients that greatly simplify the authentication process.

   


## See also  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi///PointingToDeclaration/"></a>| <a name="com.sorrowblue.twitlin.authentication/OAuthApi///PointingToDeclaration/"></a><br><br><a href="https://developer.twitter.com/en/docs/authentication/oauth-1-0a">OAuth 1.0a</a><br><br>
  


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>[accessToken](access-token.md)| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [accessToken](access-token.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), oauthVerifier: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[AccessToken](../-access-token/index.md)>  <br>More info  <br>Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token.  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>[authenticate](authenticate.md)| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [authenticate](authenticate.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), forceLogin: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br>More info  <br>Allows a Consumer application to use an OAuth request_token to request user authorization.  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>[authorize](authorize.md)| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [authorize](authorize.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), forceLogin: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br>More info  <br>Allows a Consumer application to use an OAuth Request Token to request user authorization.  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/invalidateToken/#/PointingToDeclaration/"></a>[invalidateToken](invalidate-token.md)| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/invalidateToken/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [invalidateToken](invalidate-token.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[InvalidateToken](../-invalidate-token/index.md)>  <br>More info  <br>Allows a registered application to revoke an issued OAuth access_token by presenting its client credentials.  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a>[requestToken](request-token.md)| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [requestToken](request-token.md)(oauthCallback: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), xAuthAccessType: [XAuthAccessType](../-x-auth-access-type/index.md)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[RequestToken](../-request-token/index.md)>  <br>More info  <br>Allows a Consumer application to obtain an OAuth Request Token to request user authorization.  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

