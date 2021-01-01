//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendshipsApi](index.md)/[outgoing](outgoing.md)



# outgoing  
[common]  
Content  
abstract suspend fun [outgoing](outgoing.md)(cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1"): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingIds](../-paging-ids/index.md)>  
More info  


Returns a collection of numeric IDs for every protected user for whom the authenticating user has a pending follow request.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/outgoing/#kotlin.String/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/outgoing/#kotlin.String/PointingToDeclaration/"></a><br><br>Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingIds.previousCursor](../-paging-ids/previous-cursor.md) and [PagingIds.nextCursor](../-paging-ids/next-cursor.md) to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
  
  



