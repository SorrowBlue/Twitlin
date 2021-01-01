//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendsApi](index.md)



# FriendsApi  
 [common] interface [FriendsApi](index.md)

The following API endpoints can be used to programmatically follow users, search for users, and get user information:

<ul><li>[FriendsApi.ids](ids.md)</li><li>[FriendsApi.list](list.md)</li></ul>

For more details, please see the individual endpoint information within the API reference section.



###  Terminology  


To avoid confusion around the term "friends" and "followers" with respect to the API endpoints, below is a definition of each:



*Friends* - we refer to "friends" as the Twitter users that a specific user follows (e.g., following). Therefore, the [FriendsApi.ids](ids.md) endpoint returns a collection of user IDs that the specified user follows.



*Followers* - refers to the Twitter users that follow a specific user. Therefore, making a request to the [FollowersApi.ids](../-followers-api/ids.md) endpoint returns a collection of user IDs for every user following the specified user.

   


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>[ids](ids.md)| <a name="com.sorrowblue.twitlin.users/FriendsApi/ids/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [ids](ids.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1", count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingIds](../-paging-ids/index.md)>  <br>More info  <br>Returns a cursored collection of user IDs for every user the specified user is following (otherwise known as their "friends").  <br><br><br>
| <a name="com.sorrowblue.twitlin.users/FriendsApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>[list](list.md)| <a name="com.sorrowblue.twitlin.users/FriendsApi/list/#kotlin.String?#kotlin.String?#kotlin.String#kotlin.Int#kotlin.Boolean#kotlin.Boolean/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [list](list.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, cursor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "-1", count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 20, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, includeUserEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[PagingUser](../-paging-user/index.md)>  <br>More info  <br>Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their "friends").  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

