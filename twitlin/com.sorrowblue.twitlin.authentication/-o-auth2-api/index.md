//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuth2Api](index.md)



# OAuth2Api  
 [common] interface [OAuth2Api](index.md)

##  OAuth 2.0 Bearer Token  


OAuth 2.0 Bearer Token authenticates requests on behalf of your [developer App](https://developer.twitter.com/en/docs/apps). As this method is specific to the App, it does not involve any users. This method is typically for developers that need read-only access to public information.



This authentication method requires for you to pass a Bearer Token with your request, which you can generate within the Keys and tokens section of your developer Apps. Here is an example of what a request looks like with a fake bearer token plugged in:



API calls using app-only authentication are rate limited per endpoint at the App level.



To use this method, you'll need a bearer token, which you can generate by passing your consumer key and secret through the [OAuth2Api.token](token.md) endpoint or from the keys and token section of your App settings in the developer portal. If you'd like to revoke a bearer token, you can use the [OAuth2Api.invalidateToken](invalidate-token.md) endpoint or click where it says revoke next to the bearer token in the keys and tokens section of your App settings.



###  Next steps  
<ul><li>[See more about app-only authentication](https://developer.twitter.com/en/docs/authentication/oauth-2-0/application-only)</li><li>[Learn about bearer tokens](https://developer.twitter.com/en/docs/authentication/oauth-2-0/bearer-tokens)</li></ul>   


## See also  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api///PointingToDeclaration/"></a>| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api///PointingToDeclaration/"></a><br><br><a href="https://developer.twitter.com/en/docs/authentication/oauth-2-0">OAuth 2.0 Bearer Token</a><br><br>
  


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api/invalidateToken/#/PointingToDeclaration/"></a>[invalidateToken](invalidate-token.md)| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api/invalidateToken/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [invalidateToken](invalidate-token.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[InvalidateToken](../-invalidate-token/index.md)>  <br>More info  <br>Allows a registered application to revoke an issued oAuth 2.0 Bearer Token by presenting its client credentials.  <br><br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api/token/#/PointingToDeclaration/"></a>[token](token.md)| <a name="com.sorrowblue.twitlin.authentication/OAuth2Api/token/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [token](token.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[BearerToken](../-bearer-token/index.md)>  <br>More info  <br>Allows a registered application to obtain an OAuth 2 Bearer Token, which can be used to make API requests on an application's own behalf, without a user context.  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

