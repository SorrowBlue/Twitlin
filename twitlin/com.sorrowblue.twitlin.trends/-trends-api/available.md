//[twitlin](../../index.md)/[com.sorrowblue.twitlin.trends](../index.md)/[TrendsApi](index.md)/[available](available.md)



# available  
[common]  
Content  
abstract suspend fun [available](available.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlaces](../-trend-places/index.md)>>  
More info  


Returns the locations that Twitter has trending topic information for.



The response is an array of "locations" that encode the location's WOEID and some other human-readable information such as a canonical name and country the location belongs in.



Note: This endpoint uses the "where on earth identifier" or WOEID, which is a legacy identifier created by Yahoo and has been deprecated. Twitter API v1.1 still uses the numeric value to identify town and country trend locations. Reference our legacy [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html) , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0). The url returned in the response, [where.yahooapis.com](https://where.yahooapis.com) is no longer valid.



#### Return  


TODO

  



