//[twitlin](../../../index.md)/[com.sorrowblue.twitlin.objects](../../index.md)/[Entities](../index.md)/[Poll](index.md)



# Poll  
 [common] data class [Poll](index.md)(**options**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Entities.Poll.Option](-option/index.md)>, **endDatetime**: LocalDateTime, **durationMinutes**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

The [TwitterTweet.entities](../../-twitter-tweet/entities.md) section will contain a [polls](../polls.md) array containing a single [Poll](index.md) object if the Tweet contains a poll. If no poll is included, there will be no [polls](../polls.md) array in the entities section.



Note that these Poll metadata are only available with the following Enterprise APIs:



<ul> <li>Volume streams ([Decahose](https://developer.twitter.com/en/docs/tweets/sample-realtime/overview/decahose))</li> <li>[Real-time PowerTrack](https://developer.twitter.com/en/docs/tweets/filter-realtime/overview/powertrack-api)</li> <li>[Historical PowerTrack](https://developer.twitter.com/en/docs/tweets/batch-historical/overview)</li> <li>Twitter Search APIs ([Full-Archive Search](https://developer.twitter.com/en/docs/tweets/search/overview/full-archive-search) and [30-Day Search](https://developer.twitter.com/en/docs/tweets/search/overview/30-day-search)</li>

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/Poll/#kotlin.collections.List[com.sorrowblue.twitlin.objects.Entities.Poll.Option]#kotlinx.datetime.LocalDateTime#kotlin.Int/PointingToDeclaration/"></a>[Poll](-poll.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/Poll/#kotlin.collections.List[com.sorrowblue.twitlin.objects.Entities.Poll.Option]#kotlinx.datetime.LocalDateTime#kotlin.Int/PointingToDeclaration/"></a> [common] fun [Poll](-poll.md)(options: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Entities.Poll.Option](-option/index.md)>, endDatetime: LocalDateTime, durationMinutes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll.Option///PointingToDeclaration/"></a>[Option](-option/index.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll.Option///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Option](-option/index.md)(**position**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **text**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>An array of options, each having a poll position, and the text for that position.  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Entities.Poll.Option](-option/index.md)>  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): LocalDateTime  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component3/#/PointingToDeclaration/"></a>[component3](component3.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/component3/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component3](component3.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/copy/#kotlin.collections.List[com.sorrowblue.twitlin.objects.Entities.Poll.Option]#kotlinx.datetime.LocalDateTime#kotlin.Int/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/copy/#kotlin.collections.List[com.sorrowblue.twitlin.objects.Entities.Poll.Option]#kotlinx.datetime.LocalDateTime#kotlin.Int/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(options: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Entities.Poll.Option](-option/index.md)>, endDatetime: LocalDateTime, durationMinutes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Entities.Poll](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/durationMinutes/#/PointingToDeclaration/"></a>[durationMinutes](duration-minutes.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/durationMinutes/#/PointingToDeclaration/"></a> [common] val [durationMinutes](duration-minutes.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)Duration of poll in minutes.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/endDatetime/#/PointingToDeclaration/"></a>[endDatetime](end-datetime.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/endDatetime/#/PointingToDeclaration/"></a> [common] val [endDatetime](end-datetime.md): LocalDateTimeTime stamp (UTC) of when poll ends.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/options/#/PointingToDeclaration/"></a>[options](options.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Poll/options/#/PointingToDeclaration/"></a> [common] val [options](options.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Entities.Poll.Option](-option/index.md)>An array of options, each having a poll position, and the text for that position.   <br>

