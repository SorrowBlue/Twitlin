//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuthApi](index.md)/[requestToken](request-token.md)



# requestToken  
[common]  
Content  
abstract suspend fun [requestToken](request-token.md)(oauthCallback: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), xAuthAccessType: [XAuthAccessType](../-x-auth-access-type/index.md)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[RequestToken](../-request-token/index.md)>  
More info  


Allows a Consumer application to obtain an OAuth Request Token to request user authorization. This method fulfills [Section 6.1](https://oauth.net/core/1.0/#auth_step1) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).



***We require you use HTTPS for all OAuth authorization steps.***



***Usage Note:*** Only ASCII values are accepted for the oauth_nonce



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a>oauthCallback| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a><br><br>TODO<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a>xAuthAccessType| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/requestToken/#kotlin.String#com.sorrowblue.twitlin.authentication.XAuthAccessType?/PointingToDeclaration/"></a><br><br>TODO<br><br>
  
  



