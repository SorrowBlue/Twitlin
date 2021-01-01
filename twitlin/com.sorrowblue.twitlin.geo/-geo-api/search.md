//[twitlin](../../index.md)/[com.sorrowblue.twitlin.geo](../index.md)/[GeoApi](index.md)/[search](search.md)



# search  
[common]  
Content  
abstract suspend fun [search](search.md)(lat: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, long: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = null, query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, ip: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, granularity: [Granularity](../../com.sorrowblue.twitlin.objects/-granularity/index.md)? = null, maxResults: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[GeoSearch](../-geo-search/index.md)>  
More info  


Search for places that can be attached to a Tweet via [StatusesApi.update](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/update.md). Given a latitude and a longitude pair, an IP address, or a name, this request will return a list of all the valid places that can be used as the placeId when updating a status.



Conceptually, a query can be made from the user's location, retrieve a list of places, have the user validate the location they are at, and then send the ID of this location with a call to [StatusesApi.update](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/update.md).



This is the recommended method to use find places that can be attached to [StatusesApi.update](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/update.md). Unlike [GeoApi.reverseGeocode](reverse-geocode.md) which provides raw data access, this endpoint can potentially re-order places with regards to the user who is authenticated. This approach is also preferred for interactive place matching with the user.



Some parameters in this method are only required based on the existence of other parameters. For instance, lat is required if long is provided, and vice-versa. Authentication is recommended, but not required with this method.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>lat| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The latitude to search around. This parameter will be ignored unless it is inside the range -90.0 to +90.0 (North is positive) inclusive. It will also be ignored if there isn't a corresponding long parameter.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>long| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The longitude to search around. The valid ranges for longitude are -180.0 to +180.0 (East is positive) inclusive. This parameter will be ignored if outside that range, if it is not a number, if geo_enabled is turned off, or if there not a corresponding lat parameter.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>query| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>Free-form text to match against while executing a geo-based query, best suited for finding nearby locations by name. Remember to URL encode the query.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>ip| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>An IP address. Used when attempting to fix geolocation based off of the user's IP address.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>granularity| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>This is the minimal granularity of place types to return and must be one of: [Granularity.NEIGHBORHOOD](../../com.sorrowblue.twitlin.objects/-granularity/-n-e-i-g-h-b-o-r-h-o-o-d/index.md), [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), [Granularity.ADMIN](../../com.sorrowblue.twitlin.objects/-granularity/-a-d-m-i-n/index.md) or [Granularity.COUNTRY](../../com.sorrowblue.twitlin.objects/-granularity/-c-o-u-n-t-r-y/index.md). If no granularity is provided for the request neighborhood is assumed. Setting this to [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), for example, will find places which have a type of [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), [Granularity.ADMIN](../../com.sorrowblue.twitlin.objects/-granularity/-a-d-m-i-n/index.md) or [Granularity.COUNTRY](../../com.sorrowblue.twitlin.objects/-granularity/-c-o-u-n-t-r-y/index.md).<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>maxResults| <a name="com.sorrowblue.twitlin.geo/GeoApi/search/#kotlin.Double?#kotlin.Double?#kotlin.String?#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>A hint as to the number of results to return. This does not guarantee that the number of results returned will equal max_results, but instead informs how many "nearby" results to return. Ideally, only pass in the number of places you intend to display to the user here.<br><br>
  
  



