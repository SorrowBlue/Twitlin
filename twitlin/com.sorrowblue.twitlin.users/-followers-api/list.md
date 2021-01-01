//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FollowersApi](index.md)/[list](list.md)



# list  
[common]  
Content  
abstract suspend fun [list](list.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1", count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, includeUserEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingUser](../-paging-user/index.md)>  
More info  


Returns a cursored collection of user objects for users following the specified user.



At this time, results are ordered with the most recent following first — however, this ordering is subject to unannounced change and eventual consistency issues. Results are given in groups of 20 users and multiple "pages" of results can be navigated through using the [PagingUser.nextCursor](../-paging-user/next-cursor.md) value in subsequent requests. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results.<br><br>
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results.<br><br>
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>Causes the results to be broken into pages. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingUser.previousCursor](../-paging-user/previous-cursor.md) and [PagingUser.nextCursor](../-paging-user/next-cursor.md) to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The number of users to return per page, up to a maximum of 200. Defaults to 20.<br><br>
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects. If set to any other value, statuses will be included.<br><br>
| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>includeUserEntities| <a name="com.sorrowblue.twitlin.users/FollowersApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a><br><br>The user object [TwitterUser.entities](../../com.sorrowblue.twitlin.objects/-twitter-user/entities.md) node will not be included when set to false.<br><br>
  
  



