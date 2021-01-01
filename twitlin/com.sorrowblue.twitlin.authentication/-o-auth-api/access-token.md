//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuthApi](index.md)/[accessToken](access-token.md)



# accessToken  
[common]  
Content  
abstract suspend fun [accessToken](access-token.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), oauthVerifier: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[AccessToken](../-access-token/index.md)>  
More info  


Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token. This method fulfills [Section 6.3](http://oauth.net/core/1.0/#auth_step3) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).



#### Return  


Authenticated access token



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>oauthToken| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>The oauth_token here must be the same as the oauth_token returned in the request_token step.<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>oauthVerifier| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/accessToken/#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>If using the OAuth web-flow, set this parameter to the value of the oauth_verifier returned in the callback URL. If you are using out-of-band OAuth, set this value to the pin-code. For OAuth 1.0a compliance this parameter is **required**. OAuth 1.0a is strictly enforced and applications not using the oauth_verifier will fail to complete the OAuth flow.<br><br>
  
  



