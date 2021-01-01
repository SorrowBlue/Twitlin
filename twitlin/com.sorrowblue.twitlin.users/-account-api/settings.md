//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[AccountApi](index.md)/[settings](settings.md)



# settings  
[common]  
Content  
abstract suspend fun [settings](settings.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Settings](../-settings/index.md)>  
More info  


Returns settings (including current trend, geo and sleep time information) for the authenticating user.



#### Return  


TODO

  


[common]  
Content  
abstract suspend fun [settings](settings.md)(sleepTimeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, startSleepTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, endSleepTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, timeZone: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, trendLocationWoeid: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, lang: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Settings](../-settings/index.md)>  
More info  


Updates the authenticating user's settings.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>sleepTimeEnabled| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>When set to true, will enable sleep time for the user. Sleep time is the time when push or SMS notifications should not be sent to the user.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>startSleepTime| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>The hour that sleep time should begin if it is enabled. The value for this parameter should be provided in [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23). The time is considered to be in the same timezone as the user's timeZone setting.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>endSleepTime| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>The hour that sleep time should end if it is enabled. The value for this parameter should be provided in [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23). The time is considered to be in the same timezone as the user's timeZone setting.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>timeZone| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>The timezone dates and times should be displayed in for the user. The timezone must be one of the [Rails TimeZone](http://api.rubyonrails.org/classes/ActiveSupport/TimeZone.html) names.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>trendLocationWoeid| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>The Yahoo! Where On Earth ID to use as the user's default trend location. Global information is available by using 1 as the WOEID. The WOEID must be one of the locations returned by [TrendsApi.available](../../com.sorrowblue.twitlin.trends/-trends-api/available.md).<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a>lang| <a name="com.sorrowblue.twitlin.users/AccountApi/settings/#kotlin.Boolean?#kotlin.Int?#kotlin.Int?#kotlin.String?#kotlin.Int?#kotlin.String?/PointingToDeclaration/"></a><br><br>The language which Twitter should render in for this user. The language must be specified by the appropriate two letter ISO 639-1 representation. Currently supported languages are provided by [this endpoint](https://developer.twitter.com/en/docs/developer-utilities/supported-languages/api-reference/get-help-languages).<br><br>
  
  



