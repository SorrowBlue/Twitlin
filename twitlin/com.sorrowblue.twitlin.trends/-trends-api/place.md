//[twitlin](../../index.md)/[com.sorrowblue.twitlin.trends](../index.md)/[TrendsApi](index.md)/[place](place.md)



# place  
[common]  
Content  
abstract suspend fun [place](place.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), exclude: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TrendPlaces](../-trend-places/index.md)>>  
More info  


Returns the top 50 trending topics for a specific id, if trending information is available for it.



Note: The id parameter for this endpoint is the "where on earth identifier" or WOEID, which is a legacy identifier created by Yahoo and has been deprecated. Twitter API v1.1 still uses the numeric value to identify town and country trend locations. Reference our legacy [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html) , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0).



Example WOEID locations include: Worldwide: 1 UK: 23424975 Brazil: 23424768 Germany: 23424829 Mexico: 23424900 Canada: 23424775 United States: 23424977 New York: 2459115



To identify other ids, please use the [TrendsApi.available](available.md) endpoint.



The response is an array of [Trend](../-trend/index.md) that encode the name of the trending topic, the query parameter that can be used to search for the topic on [Twitter Search](http://search.twitter.com/), and the Twitter Search URL.



The most up to date info available is returned on request. The [TrendPlaces.createdAt](../-trend-places/created-at.md) field will show when the oldest trend started trending. The [TrendPlaces.asOf](../-trend-places/as-of.md) field contains the timestamp when the list of trends was created.



The [Trend.tweetVolume](../-trend/tweet-volume.md) for the last 24 hours is also returned for many trends if this is available.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a>id| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The numeric value that represents the location from where to return trending information for from. Formerly linked to the Yahoo! Where On Earth ID Global information is available by using 1 as the WOEID.<br><br>
| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a>exclude| <a name="com.sorrowblue.twitlin.trends/TrendsApi/place/#kotlin.Int#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Setting this equal to hashtags will remove all hashtags from the trends list.<br><br>
  
  



