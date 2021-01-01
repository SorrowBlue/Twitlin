//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[show](show.md)



# show  
[common]  
Content  
abstract suspend fun [show](show.md)(listId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterList](../-twitter-list/index.md)>  
More info  


Returns the specified list. Private lists will only be shown if the authenticated user owns the specified list.



#### Return  


Twitter list corresponding to list_id



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.Long/PointingToDeclaration/"></a>listId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.Long/PointingToDeclaration/"></a><br><br>Twitter list corresponding to list_id<br><br>
  
  


[common]  
Content  
abstract suspend fun [show](show.md)(slug: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ownerScreenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, ownerId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterList](../-twitter-list/index.md)>  
More info  


Returns the specified list. Private lists will only be shown if the authenticated user owns the specified list.



#### Return  


Specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a>slug| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a><br><br>You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the ownerId or ownerScreenName parameters.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a>ownerScreenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a><br><br>The screen name of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a>ownerId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/show/#kotlin.String#kotlin.String?#kotlin.Long?/PointingToDeclaration/"></a><br><br>The user ID of the user who owns the list being requested by a slug.<br><br>
  
  



