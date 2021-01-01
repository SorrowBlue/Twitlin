//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[UsersApi](index.md)/[lookup](lookup.md)



# lookup  
[common]  
Content  
abstract suspend fun [lookup](lookup.md)(screenName: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, userId: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, tweetMode: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>>  
More info  


Returns fully-hydrated [TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md) for up to 100 users per request, as specified by comma-separated values passed to the userId and/or screenName parameters.



This method is especially useful when used in conjunction with collections of user IDs returned from [FriendsApi.ids](../-friends-api/ids.md) and [FollowersApi.ids](../-followers-api/ids.md).



[UsersApi.show](show.md) is used to retrieve a single user object.



There are a few things to note when using this method.

<ul><li>You must be following a protected user to be able to see their most recent status update. If you don't follow a protected user their status will be removed.</li><li>The order of user IDs or screen names may not match the order of users in the returned array.</li><li>If a requested user is unknown, suspended, or deleted, then that user will not be returned in the results list.</li><li>If none of your lookup criteria can be satisfied by returning a user object, a HTTP 404 will be thrown.</li><li>You are strongly encouraged to use a POST for larger requests.</li></ul>

#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>A comma separated list of screen names, up to 100 are allowed in a single request. You are strongly encouraged to use a POST for larger (up to 100 screen names) requests.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>A comma separated list of user IDs, up to 100 are allowed in a single request. You are strongly encouraged to use a POST for larger requests.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The [TwitterUser.entities](../../com.sorrowblue.twitlin.objects/-twitter-user/entities.md) node that may appear within embedded statuses will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>tweetMode| <a name="com.sorrowblue.twitlin.users/UsersApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Valid request values are compat and extended, which give compatibility mode and extended mode, respectively for Tweets that contain over 140 characters.<br><br>
  
  



