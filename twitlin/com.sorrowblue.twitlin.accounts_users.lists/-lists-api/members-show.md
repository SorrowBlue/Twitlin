//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[membersShow](members-show.md)



# membersShow  
[common]  
Content  
abstract suspend fun [membersShow](members-show.md)(listId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, userId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Check if the specified user is a member of the specified list.



#### Return  


specified user is a member of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>listId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The numerical id of the list. Required if there is no userId.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screen name. Required if there is no listId.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true each tweet will include a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions](../../com.sorrowblue.twitlin.objects/-entities/user-mentions.md), [com.sorrowblue.twitlin.objects.Entities.urls](../../com.sorrowblue.twitlin.objects/-entities/urls.md), and [com.sorrowblue.twitlin.objects.Entities.hashtags](../../com.sorrowblue.twitlin.objects/-entities/hashtags.md). While entities are opt-in on timelines at present, they will be made a default component of output in the future. See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  


[common]  
Content  
abstract suspend fun [membersShow](members-show.md)(slug: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ownerScreenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, ownerId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Check if the specified user is a member of the specified list.



#### Return  


specified user is a member of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>slug| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the ownerId or ownerScreenName parameters.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerScreenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The user ID of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true each tweet will include a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions](../../com.sorrowblue.twitlin.objects/-entities/user-mentions.md), [com.sorrowblue.twitlin.objects.Entities.urls](../../com.sorrowblue.twitlin.objects/-entities/urls.md), and [com.sorrowblue.twitlin.objects.Entities.hashtags](../../com.sorrowblue.twitlin.objects/-entities/hashtags.md). While entities are opt-in on timelines at present, they will be made a default component of output in the future. See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  


[common]  
Content  
abstract suspend fun [membersShow](members-show.md)(screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Check if the specified user is a member of the specified list.



#### Return  


specified user is a member of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true each tweet will include a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions](../../com.sorrowblue.twitlin.objects/-entities/user-mentions.md), [com.sorrowblue.twitlin.objects.Entities.urls](../../com.sorrowblue.twitlin.objects/-entities/urls.md), and [com.sorrowblue.twitlin.objects.Entities.hashtags](../../com.sorrowblue.twitlin.objects/-entities/hashtags.md). While entities are opt-in on timelines at present, they will be made a default component of output in the future. See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/membersShow/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true statuses will not be included in the returned user objects.<br><br>
  
  



