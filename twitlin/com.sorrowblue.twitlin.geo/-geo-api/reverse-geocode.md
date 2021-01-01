//[twitlin](../../index.md)/[com.sorrowblue.twitlin.geo](../index.md)/[GeoApi](index.md)/[reverseGeocode](reverse-geocode.md)



# reverseGeocode  
[common]  
Content  
abstract suspend fun [reverseGeocode](reverse-geocode.md)(lat: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), long: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), accuracy: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, granularity: [Granularity](../../com.sorrowblue.twitlin.objects/-granularity/index.md)? = null, maxResults: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[ReverseGeocode](../-reverse-geocode/index.md)>  
More info  


Given a latitude and a longitude, searches for up to 20 places that can be used as a placeId when updating a status.



This request is an informative call and will deliver generalized results about geography.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>lat| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The latitude to search around. This parameter will be ignored unless it is inside the range -90.0 to +90.0 (North is positive) inclusive. It will also be ignored if there isn't a corresponding long parameter.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>long| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The longitude to search around. The valid ranges for longitude are -180.0 to +180.0 (East is positive) inclusive. This parameter will be ignored if outside that range, if it is not a number, if geo_enabled is turned off, or if there not a corresponding lat parameter.<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>accuracy| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>A hint on the "region" in which to search. If a number, then this is a radius in meters, but it can also take a string that is suffixed with ft to specify feet. If this is not passed in, then it is assumed to be 0m. If coming from a device, in practice, this value is whatever accuracy the device has measuring its location (whether it be coming from a GPS, WiFi triangulation, etc.).<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>granularity| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>This is the minimal granularity of place types to return and must be one of: [Granularity.NEIGHBORHOOD](../../com.sorrowblue.twitlin.objects/-granularity/-n-e-i-g-h-b-o-r-h-o-o-d/index.md), [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), [Granularity.ADMIN](../../com.sorrowblue.twitlin.objects/-granularity/-a-d-m-i-n/index.md) or [Granularity.COUNTRY](../../com.sorrowblue.twitlin.objects/-granularity/-c-o-u-n-t-r-y/index.md). If no granularity is provided for the request neighborhood is assumed. Setting this to [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), for example, will find places which have a type of [Granularity.CITY](../../com.sorrowblue.twitlin.objects/-granularity/-c-i-t-y/index.md), [Granularity.ADMIN](../../com.sorrowblue.twitlin.objects/-granularity/-a-d-m-i-n/index.md) or [Granularity.COUNTRY](../../com.sorrowblue.twitlin.objects/-granularity/-c-o-u-n-t-r-y/index.md).<br><br>
| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a>maxResults| <a name="com.sorrowblue.twitlin.geo/GeoApi/reverseGeocode/#kotlin.Double#kotlin.Double#kotlin.String?#com.sorrowblue.twitlin.objects.Granularity?#kotlin.Int?/PointingToDeclaration/"></a><br><br>A hint as to the number of results to return. This does not guarantee that the number of results returned will equal max_results, but instead informs how many "nearby" results to return. Ideally, only pass in the number of places you intend to display to the user here.<br><br>
  
  



