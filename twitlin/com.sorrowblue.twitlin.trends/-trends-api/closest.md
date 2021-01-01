//[twitlin](../../index.md)/[com.sorrowblue.twitlin.trends](../index.md)/[TrendsApi](index.md)/[closest](closest.md)



# closest  
[common]  
Content  
abstract suspend fun [closest](closest.md)(lat: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), long: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlace](../-trend-place/index.md)>>  
More info  


Returns the locations that Twitter has trending topic information for, closest to a specified location.



The response is an array of "locations" that encode the location's WOEID and some other human-readable information such as a canonical name and country the location belongs in.



Note: The "where on earth identifier" or WOEID, is a legacy identifier created by Yahoo and has been deprecated. Twitter API v1.1 still uses the numeric value to identify town and country trend locations. Reference our legacy [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html) , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0). The url returned in the response, [where.yahooapis.com](https://where.yahooapis.com) is no longer valid.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a>lat| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a><br><br>If provided with a long parameter the available trend locations will be sorted by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.<br><br>
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a>long| <a name="com.sorrowblue.twitlin.trends/TrendsApi/closest/#kotlin.Double#kotlin.Double/PointingToDeclaration/"></a><br><br>If provided with a lat parameter the available trend locations will be sorted by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.<br><br>
  
  



