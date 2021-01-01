//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendshipsApi](index.md)/[incoming](incoming.md)



# incoming  
[common]  
Content  
abstract suspend fun [incoming](incoming.md)(cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1"): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingIds](../-paging-ids/index.md)>  
More info  


Returns a collection of numeric IDs for every user who has a pending request to follow the authenticating user.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/incoming/#kotlin.String/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/incoming/#kotlin.String/PointingToDeclaration/"></a><br><br>Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a [PagingIds.previousCursor](../-paging-ids/previous-cursor.md) and [PagingIds.nextCursor](../-paging-ids/next-cursor.md) to allow paging back and forth.<br><br>
  
  



