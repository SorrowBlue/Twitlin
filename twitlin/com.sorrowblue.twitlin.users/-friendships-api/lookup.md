//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendshipsApi](index.md)/[lookup](lookup.md)



# lookup  
[common]  
Content  
abstract suspend fun [lookup](lookup.md)(screenName: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, userId: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Relationship](../-relationship/index.md)>>  
More info  


Returns the relationships of the authenticating user to the comma-separated list of up to 100screenName or userId provided. Values for [Relationship.connections](../-relationship/connections.md) can be: [Relationship.Connection.FOLLOWING](../-relationship/-connection/-f-o-l-l-o-w-i-n-g/index.md), [Relationship.Connection.FOLLOWING_REQUESTED](../-relationship/-connection/-f-o-l-l-o-w-i-n-g_-r-e-q-u-e-s-t-e-d/index.md), [Relationship.Connection.FOLLOWED_BY](../-relationship/-connection/-f-o-l-l-o-w-e-d_-b-y/index.md), [Relationship.Connection.NONE](../-relationship/-connection/-n-o-n-e/index.md), [Relationship.Connection.BLOCKING](../-relationship/-connection/-b-l-o-c-k-i-n-g/index.md),[Relationship.Connection.MUTING](../-relationship/-connection/-m-u-t-i-n-g/index.md).



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>A comma separated list of screen names, up to 100 are allowed in a single request.<br><br>
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/lookup/#kotlin.collections.List[kotlin.String]?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>A comma separated list of user IDs, up to 100 are allowed in a single request.<br><br>
  
  



