//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[ownerships](ownerships.md)



# ownerships  
[common]  
Content  
abstract suspend fun [ownerships](ownerships.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, count: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, cursor: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingTwitterList](../-paging-twitter-list/index.md)>  
More info  


Returns the lists owned by the specified Twitter user. Private lists will only be shown if the authenticated user is also the owner of the lists.



#### Return  


Paged Twitter List



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screenName. Required if there is no screenName.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a userId. Required if there is no userId.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a><br><br>The amount of results to return per page. Defaults to 20. No more than 1,000 results will ever be returned in a single page.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a>cursor| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/ownerships/#kotlin.String?#kotlin.String?#kotlin.Long?#kotlin.Long?/PointingToDeclaration/"></a><br><br>Breaks the results into pages. Provide a value of -1 to begin paging. Provide values as returned in the response body's [PagingTwitterList.nextCursor](../-paging-twitter-list/next-cursor.md) and [PagingTwitterList.previousCursor](../-paging-twitter-list/previous-cursor.md) attributes to page back and forth in the list. It is recommended to always use cursors when the method supports them. See [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.<br><br>
  
  



