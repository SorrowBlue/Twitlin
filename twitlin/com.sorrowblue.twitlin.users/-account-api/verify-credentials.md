//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[AccountApi](index.md)/[verifyCredentials](verify-credentials.md)



# verifyCredentials  
[common]  
Content  
abstract suspend fun [verifyCredentials](verify-credentials.md)(includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, includeEmail: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Returns an HTTP 200 OK response code and a representation of the requesting user if authentication was successful; returns a 401 status code and an error message if not. Use this method to test if supplied user credentials are valid.



###  Request a User's Email Address  


The "Request email addresses from users" checkbox is available under the app permissions on [developer.twitter.com](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps). Privacy Policy URL and Terms of Service URL fields must be completed in the app settings in order for email address access to function. If enabled, users will be informed via the [OAuthApi.authorize](../../com.sorrowblue.twitlin.authentication/-o-auth-api/authorize.md) dialog that your app can access their email address.



*Please note* - Your app will need to regenerate the user access tokens for previously authenticated users to access their email address.



*Please note* - You can view and edit your existing [Twitter apps](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps) via the [Twitter app dashboard](https://developer.twitter.com/en/apps) if you are logged into your Twitter account on developer.twitter.com.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true , t or 1 statuses will not be included in the returned user object.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEmail| <a name="com.sorrowblue.twitlin.users/AccountApi/verifyCredentials/#kotlin.Boolean?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to true email will be returned in the user objects as a string. If the user does not have an email address on their account, or if the email address is not verified, null will be returned.<br><br>
  
  



