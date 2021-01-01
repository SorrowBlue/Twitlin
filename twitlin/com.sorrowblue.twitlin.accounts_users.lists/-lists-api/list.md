//[twitlin](../../index.md)/[com.sorrowblue.twitlin.accounts_users.lists](../index.md)/[ListsApi](index.md)/[list](list.md)



# list  
[common]  
Content  
abstract suspend fun [list](list.md)(userId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, reverse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TwitterList](../-twitter-list/index.md)>>  
More info  


Returns all lists the authenticating or specified user subscribes to, including their own. The user is specified using the userId or screenName parameters. If no user is given, the authenticating user is used. A maximum of 100 results will be returned by this call. Subscribed lists are returned first, followed by owned lists. This means that if a user subscribes to 90 lists and owns 20 lists, this method returns 90 subscriptions and 10 owned lists. The reverse method returns owned lists first, so with [reverse]=true, 20 owned lists and 80 subscriptions would be returned. If your goal is to obtain every list a user owns or subscribes to, use [ListsApi.ownerships](ownerships.md) and/or [ListsApi.subscribers](subscribers.md) instead.



#### Return  


List of Twitter users



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screen name.<br /> **Note: ** Specifies the ID of the user to get lists from. Helpful for disambiguating when a valid user ID is also a valid screen name.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>reverse| <a name="com.sorrowblue.twitlin.accounts_users.lists/ListsApi/list/#kotlin.Int?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Set this to true if you would like owned lists to be returned first. See description above for information on how this parameter works.<br><br>
  
  



