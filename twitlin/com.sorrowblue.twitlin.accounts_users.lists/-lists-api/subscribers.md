//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[subscribers](subscribers.md)



# subscribers  
[common]  
Content  
abstract suspend fun [subscribers](subscribers.md)(listId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), count: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, cursor: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingTwitterUser](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/index.md)>  
More info  


Returns the subscribers of the specified list. Private list subscribers will only be shown if the authenticated user owns the specified list.



#### Return  


The subscribers of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>listId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The numerical id of the list.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the number of results to return per page (see cursor below). The default is 20, with a maximum of 5,000.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned in the response body's [PagingTwitterUser.nextCursor](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/next-cursor.md) and [PagingTwitterUser.previousCursor](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/previous-cursor.md) attributes to page back and forth in the list. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true each tweet will include a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including: user_mentions, urls, and hashtags. While entities are opt-in on timelines at present, they will be made a default component of output in the future. See Tweet Entities for more details.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  


[common]  
Content  
abstract suspend fun [subscribers](subscribers.md)(slug: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ownerScreenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, ownerId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, count: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, cursor: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingTwitterUser](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/index.md)>  
More info  


Returns the subscribers of the specified list. Private list subscribers will only be shown if the authenticated user owns the specified list.



#### Return  


The subscribers of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>slug| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the ownerId or ownerScreenName parameters.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerScreenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The user ID of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the number of results to return per page (see cursor below). The default is 20, with a maximum of 5,000.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned in the response body's [PagingTwitterUser.nextCursor](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/next-cursor.md) and [PagingTwitterUser.previousCursor](../../com.sorrowblue.twitlin.objects/-paging-twitter-user/previous-cursor.md) attributes to page back and forth in the list. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true each tweet will include a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including: user_mentions, urls, and hashtags. While entities are opt-in on timelines at present, they will be made a default component of output in the future. See Tweet Entities for more details.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/subscribers/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  



