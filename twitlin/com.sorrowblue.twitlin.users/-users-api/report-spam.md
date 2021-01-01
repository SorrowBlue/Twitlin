//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[UsersApi](index.md)/[reportSpam](report-spam.md)



# reportSpam  
[common]  
Content  
abstract suspend fun [reportSpam](report-spam.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, performBlock: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Report the specified user as a spam account to Twitter. Additionally, optionally performs the equivalent of [BlocksApi.create](../-blocks-api/create.md) on behalf of the authenticated user.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The ID of the user to report as a spammer. Helpful for disambiguating when a valid user ID is also a valid screen name.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The screen_name of the user to report as a spammer. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a>performBlock| <a name="com.sorrowblue.twitlin.users/UsersApi/reportSpam/#kotlin.String?#kotlin.String?#kotlin.Boolean/PointingToDeclaration/"></a><br><br>Whether the account should be blocked by the authenticated user, as well as being reported for spam.<br><br>
  
  



