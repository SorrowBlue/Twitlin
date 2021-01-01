//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[BlocksApi](index.md)/[create](create.md)



# create  
[common]  
Content  
abstract suspend fun [create](create.md)(screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Blocks the specified user from following the authenticating user. In addition the blocked user will not show in the authenticating users mentions or timeline (unless retweeted by another user). If a follow or friend relationship exists it is destroyed.



The URL pattern /version/block/create/:screen_name_or_user_id.format is still accepted but not recommended. As a sequence of numbers is a valid screen name we recommend using the screenName or userId parameter instead.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the potentially blocked user. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The ID of the potentially blocked user. Helpful for disambiguating when a valid user ID is also a valid screen name.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/BlocksApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  



