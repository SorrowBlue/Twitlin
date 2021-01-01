//[twitlin](../../index.md)/[com.sorrowblue.twitlin.utilities](../index.md)/[ApplicationApi](index.md)/[rateLimitStatus](rate-limit-status.md)



# rateLimitStatus  
[common]  
Content  
abstract suspend fun [rateLimitStatus](rate-limit-status.md)(resourceFamily: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ResourceFamily](../-resource-family/index.md)> = emptyList()): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[RateLimitStatus](../-rate-limit-status/index.md)>  
More info  


Returns the current rate limits for methods belonging to the specified resource families.



Each API resource belongs to a "resource family" which is indicated in its method documentation. The method's resource family can be determined from the first component of the path after the resource version.



This method responds with a map of methods belonging to the families specified by the resourceFamily parameter, the current remaining uses for each of those resources within the current rate limiting window, and their expiration time in [epoch time](http://en.wikipedia.org/wiki/Unix_time). It also includes a [RateLimitStatus.rateLimitContext](../-rate-limit-status/rate-limit-context.md) field that indicates the current access token or application-only authentication context.



You may also issue requests to this method without any parameters to receive a map of all rate limited GET methods. If your application only uses a few of methods, you should explicitly provide a resourceFamily parameter with the specified resource families you work with.



When using application-only auth, this method's response indicates the application-only auth rate limiting context.



Read more about [API Rate Limiting](https://developer.twitter.com/en/docs/basics/rate-limiting) and [review the limits](https://developer.twitter.com/en/docs/basics/rate-limits).



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/ApplicationApi/rateLimitStatus/#kotlin.collections.List[com.sorrowblue.twitlin.utilities.ResourceFamily]/PointingToDeclaration/"></a>resourceFamily| <a name="com.sorrowblue.twitlin.utilities/ApplicationApi/rateLimitStatus/#kotlin.collections.List[com.sorrowblue.twitlin.utilities.ResourceFamily]/PointingToDeclaration/"></a><br><br>A comma-separated list of resource families you want to know the current rate limit disposition for. For best performance, only specify the resource families pertinent to your application. See [API Rate Limiting](https://developer.twitter.com/en/docs/basics/rate-limiting) for more information.<br><br>
  
  



