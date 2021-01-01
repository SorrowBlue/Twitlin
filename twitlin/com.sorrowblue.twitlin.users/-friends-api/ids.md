//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendsApi](index.md)/[ids](ids.md)



# ids  
[common]  
Content  
abstract suspend fun [ids](ids.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1", count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingIds](../-paging-ids/index.md)>  
More info  


Returns a cursored collection of user IDs for every user the specified user is following (otherwise known as their "friends").



At this time, results are ordered with the most recent following first — however, this ordering is subject to unannounced change and eventual consistency issues. Results are given in groups of 5,000 user IDs and multiple "pages" of results can be navigated through using the [PagingIds.nextCursor](../-paging-ids/next-cursor.md) value in subsequent requests. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.



This method is especially powerful when used in conjunction with [UsersApi.lookup](../-users-api/lookup.md), a method that allows you to convert user IDs into full [TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md) in bulk.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results.<br><br>
| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results.<br><br>
| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a><br><br>Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingIds.previousCursor](../-paging-ids/previous-cursor.md) and [PagingIds.nextCursor](../-paging-ids/next-cursor.md) to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a><br><br>Specifies the number of IDs attempt retrieval of, up to a maximum of 5,000 per distinct request. The value of count is best thought of as a limit to the number of results to return. When using the count parameter with this method, it is wise to use a consistent count value across all requests to the same user's collection. Usage of this parameter is encouraged in environments where all 5,000 IDs constitutes too large of a response.<br><br>
  
  



