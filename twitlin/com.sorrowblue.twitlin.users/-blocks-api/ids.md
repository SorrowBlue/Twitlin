//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[BlocksApi](index.md)/[ids](ids.md)



# ids  
[common]  
Content  
abstract suspend fun [ids](ids.md)(cursor: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = -1): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingIds](../-paging-ids/index.md)>  
More info  


Returns an array of numeric user ids the authenticating user is blocking.



*Important* This method is cursored, meaning your app must make multiple requests in order to receive all blocks correctly. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more details on how cursoring works.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/BlocksApi/ids/#kotlin.Long/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/BlocksApi/ids/#kotlin.Long/PointingToDeclaration/"></a><br><br>Causes the list of IDs to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingIds.previousCursor](../-paging-ids/previous-cursor.md) and [PagingIds.nextCursor](../-paging-ids/next-cursor.md) to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
  
  



