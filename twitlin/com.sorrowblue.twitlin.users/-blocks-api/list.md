//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[BlocksApi](index.md)/[list](list.md)



# list  
[common]  
Content  
abstract suspend fun [list](list.md)(cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1", includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingUser](../-paging-user/index.md)>  
More info  


Returns a collection of [TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md) that the authenticating user is blocking.



*Important* This method is cursored, meaning your app must make multiple requests in order to receive all blocks correctly. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more details on how cursoring works.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Causes the list of IDs to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingUser.previousCursor](../-paging-user/previous-cursor.md) and [PagingUser.nextCursor](../-paging-user/next-cursor.md) to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The [TwitterUser.entities](../../com.sorrowblue.twitlin.objects/-twitter-user/entities.md) node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/BlocksApi/list/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  



