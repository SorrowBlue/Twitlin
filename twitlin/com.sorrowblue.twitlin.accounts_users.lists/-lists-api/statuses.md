//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[statuses](statuses.md)



# statuses  
[common]  
Content  
abstract suspend fun [statuses](statuses.md)(listId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), sinceId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, maxId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, count: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, includeRts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TwitterTweet](../../com.sorrowblue.twitlin.objects/-twitter-tweet/index.md)>>  
More info  


Returns a timeline of tweets authored by members of the specified list. Retweets are included by default. Use the includeEntities=false parameter to omit retweets. [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines) is a great way to embed list timelines on your website.



#### Return  


A Timeline of tweets authored by members of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>listId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The numerical id of the list.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>sinceId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the sinceId, the sinceId will be forced to the oldest ID available.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>maxId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Returns results with an ID less than (that is, older than) or equal to the specified ID.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the number of results to retrieve per "page".<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Entities are ON by default in API 1.1, each tweet includes a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including: [com.sorrowblue.twitlin.objects.Entities.userMentions](../../com.sorrowblue.twitlin.objects/-entities/user-mentions.md), [com.sorrowblue.twitlin.objects.Entities.urls](../../com.sorrowblue.twitlin.objects/-entities/urls.md), and [com.sorrowblue.twitlin.objects.Entities.hashtags](../../com.sorrowblue.twitlin.objects/-entities/hashtags.md). You can omit entities from the result by using includeEntities=false.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeRts| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.Long#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true the list timeline will contain native retweets (if they exist) in addition to the standard stream of tweets. The output format of retweeted tweets is identical to the representation you see in [com.sorrowblue.twitlin.tweets.statuses.StatusesApi.homeTimeline](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/home-timeline.md).<br><br>
  
  


[common]  
Content  
abstract suspend fun [statuses](statuses.md)(slug: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ownerScreenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, ownerId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, sinceId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, maxId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, count: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, includeRts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TwitterTweet](../../com.sorrowblue.twitlin.objects/-twitter-tweet/index.md)>>  
More info  


Returns a timeline of tweets authored by members of the specified list. Retweets are included by default. Use the includeEntities=false parameter to omit retweets. [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines) is a great way to embed list timelines on your website.



#### Return  


A Timeline of tweets authored by members of the specified list



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>slug| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the ownerId or ownerScreenName parameters.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerScreenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>ownerId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The user ID of the user who owns the list being requested by a slug.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>sinceId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the sinceId, the sinceId will be forced to the oldest ID available.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>maxId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Returns results with an ID less than (that is, older than) or equal to the specified ID.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the number of results to retrieve per "page".<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Entities are ON by default in API 1.1, each tweet includes a node called "entities". This node offers a variety of metadata about the tweet in a discreet structure, including: [com.sorrowblue.twitlin.objects.Entities.userMentions](../../com.sorrowblue.twitlin.objects/-entities/user-mentions.md), [com.sorrowblue.twitlin.objects.Entities.urls](../../com.sorrowblue.twitlin.objects/-entities/urls.md), and [com.sorrowblue.twitlin.objects.Entities.hashtags](../../com.sorrowblue.twitlin.objects/-entities/hashtags.md). You can omit entities from the result by using includeEntities=false.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeRts| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/statuses/#kotlin.String#kotlin.String?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Long?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true the list timeline will contain native retweets (if they exist) in addition to the standard stream of tweets. The output format of retweeted tweets is identical to the representation you see in [com.sorrowblue.twitlin.tweets.statuses.StatusesApi.homeTimeline](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/home-timeline.md).<br><br>
  
  



