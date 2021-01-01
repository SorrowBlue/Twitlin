//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[UsersApi](index.md)/[search](search.md)



# search  
[common]  
Content  
abstract suspend fun [search](search.md)(q: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>>  
More info  


Provides a simple, relevance-based search interface to public user accounts on Twitter. Try querying by topical interest, full name, company name, location, or other criteria. Exact match searches are not supported. Only the first 1,000 matching results are available.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a>q| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The search query to run against people search.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a>page| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the page of results to retrieve.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a>count| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The number of potential user results to retrieve per page. This value has a maximum of 20.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/UsersApi/search/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included in embedded Tweet objects when set to false.<br><br>
  
  



