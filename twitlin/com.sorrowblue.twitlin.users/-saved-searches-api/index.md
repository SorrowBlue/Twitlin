//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[SavedSearchesApi](index.md)



# SavedSearchesApi  
 [common] interface [SavedSearchesApi](index.md)

With proper authorization your application can read and update a user's account and profile settings. Not all settings are exposed via the API. See the API reference for details.

   


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/create/#kotlin.String/PointingToDeclaration/"></a>[create](create.md)| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/create/#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [create](create.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[SavedSearch](../-saved-search/index.md)>  <br>More info  <br>Create a new saved search for the authenticated user.  <br><br><br>
| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/destroy/#kotlin.String/PointingToDeclaration/"></a>[destroy](destroy.md)| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/destroy/#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [destroy](destroy.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[SavedSearch](../-saved-search/index.md)>  <br>More info  <br>Destroys a saved search for the authenticating user.  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/list/#/PointingToDeclaration/"></a>[list](list.md)| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/list/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [list](list.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedSearch](../-saved-search/index.md)>>  <br>More info  <br>Returns the authenticated user's saved search queries.  <br><br><br>
| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/show/#kotlin.String/PointingToDeclaration/"></a>[show](show.md)| <a name="com.sorrowblue.twitlin.users/SavedSearchesApi/show/#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [show](show.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[SavedSearch](../-saved-search/index.md)>  <br>More info  <br>Retrieve the information for the saved search represented by the given id.  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

