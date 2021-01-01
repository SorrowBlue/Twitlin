//[twitlin](../../index.md)/[com.sorrowblue.twitlin.trends](../index.md)/[TrendsApi](index.md)



# TrendsApi  
 [common] interface [TrendsApi](index.md)

##  Get trends near a location  


An API to return the [trending topics](https://support.twitter.com/articles/101125) near a specific latitude, longitude location.



##  Get locations with trending topics  


Retrieve a full or nearby locations list of trending topics by location.

   


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/available/#/PointingToDeclaration/"></a>[available](available.md)| <a name="com.sorrowblue.twitlin.trends/TrendsApi/available/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [available](available.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlaces](../-trend-places/index.md)>>  <br>More info  <br>Returns the locations that Twitter has trending topic information for.  <br><br><br>
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a>[closest](closest.md)| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [closest](closest.md)(lat: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), long: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlace](../-trend-place/index.md)>>  <br>More info  <br>Returns the locations that Twitter has trending topic information for, closest to a specified location.  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a>[place](place.md)| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [place](place.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), exclude: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlaces](../-trend-places/index.md)>>  <br>More info  <br>Returns the top 50 trending topics for a specific id, if trending information is available for it.  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

