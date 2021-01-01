//[twitlin](../../index.md)/[com.sorrowblue.twitlin.authentication](../index.md)/[OAuthApi](index.md)/[authenticate](authenticate.md)



# authenticate  
[common]  
Content  
abstract fun [authenticate](authenticate.md)(oauthToken: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), forceLogin: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
More info  


Allows a Consumer application to use an OAuth request_token to request user authorization.



This method is a replacement of [Section 6.2](http://oauth.net/core/1.0/#auth_step2) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9) for applications using the callback authentication flow. The method will use the currently logged in user as the account for access authorization unless the forceLogin parameter is set to true.



This method differs from [OAuthApi.authorize](authorize.md) in that if the user has already granted the application permission, the redirect will occur without the user having to re-approve the application. To realize this behavior, you must enable the Use Sign in with Twitter setting on your [application record](https://developer.twitter.com/apps).



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>oauthToken| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>TODO<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>forceLogin| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>Forces the user to enter their credentials to ensure the correct users account is authorized.<br><br>
| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.authentication/OAuthApi/authenticate/#kotlin.String#kotlin.Boolean?#kotlin.String?/PointingToDeclaration/"></a><br><br>Prefills the username input box of the OAuth login screen with the given value.<br><br>
  
  



