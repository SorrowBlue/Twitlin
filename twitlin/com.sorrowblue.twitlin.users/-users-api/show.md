//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[UsersApi](index.md)/[show](show.md)



# show  
[common]  
Content  
abstract suspend fun [show](show.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Returns a [variety of information](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/user-object) about the user specified by the required userId or screenName parameter. The author's most recent Tweet will be returned inline when possible.



[UsersApi.lookup](lookup.md) is used to retrieve a bulk collection of user objects.



You must be following a protected user to be able to see their most recent Tweet. If you don't follow a protected user, the user's Tweet will be removed. A Tweet will not always be returned in the current_status field.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results. Either an userId o r screenName is required for this method.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results. Either a userId or screenName is required for this method.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/UsersApi/show/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
  
  



