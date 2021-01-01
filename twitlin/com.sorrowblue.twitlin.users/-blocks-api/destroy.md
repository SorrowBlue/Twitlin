//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[BlocksApi](index.md)/[destroy](destroy.md)



# destroy  
[common]  
Content  
abstract suspend fun [destroy](destroy.md)(screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Un-blocks the user specified in the ID parameter for the authenticating user. Returns the un-blocked user when successful. If relationships existed before the block was instantiated, they will not be restored.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the potentially blocked user. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The ID of the potentially blocked user. Helpful for disambiguating when a valid user ID is also a valid screen name.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/BlocksApi/destroy/#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  



