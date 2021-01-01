//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuthApi](index.md)/[authorize](authorize.md)



# authorize  
[common]  
Content  
abstract fun [authorize](authorize.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), forceLogin: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
More info  


Allows a Consumer application to use an OAuth Request Token to request user authorization. This method fulfills [Section 6.2](http://oauth.net/core/1.0/#auth_step2) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9). Desktop applications must use this method (and cannot use [OAuthApi.requestToken](request-token.md)).



***Usage Note:*** An oauth_Callback is never sent to this method, provide it to [OAuthApi.requestToken](request-token.md) instead.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>oauthToken| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>TODO<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>forceLogin| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>Forces the user to enter their credentials to ensure the correct users account is authorized.<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authorize/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>Prefills the username input box of the OAuth login screen with the given value.<br><br>
  
  



