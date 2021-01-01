//[twitlin](../../index.md)/[com.sorrowblue.twitlin.utilities](../index.md)/[RateLimitStatus](index.md)



# RateLimitStatus  
 [common] data class [RateLimitStatus](index.md)(**rateLimitContext**: [RateLimitStatus.RateLimitContext](-rate-limit-context/index.md), **resources**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource](-resource/index.md)>) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

TODO

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/RateLimitStatus/#com.sorrowblue.twitlin.utilities.RateLimitStatus.RateLimitContext#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource]/PointingToDeclaration/"></a>[RateLimitStatus](-rate-limit-status.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/RateLimitStatus/#com.sorrowblue.twitlin.utilities.RateLimitStatus.RateLimitContext#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource]/PointingToDeclaration/"></a> [common] fun [RateLimitStatus](-rate-limit-status.md)(rateLimitContext: [RateLimitStatus.RateLimitContext](-rate-limit-context/index.md), resources: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource](-resource/index.md)>)   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.RateLimitContext///PointingToDeclaration/"></a>[RateLimitContext](-rate-limit-context/index.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.RateLimitContext///PointingToDeclaration/"></a>[common]  <br>Content  <br>class [RateLimitContext](-rate-limit-context/index.md)(**application**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **accessToken**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>TODO  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource///PointingToDeclaration/"></a>[Resource](-resource/index.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Resource](-resource/index.md)(**family**: [ResourceFamily](../-resource-family/index.md), **info**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-resource/-info/index.md)>) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>TODO  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [RateLimitStatus.RateLimitContext](-rate-limit-context/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource](-resource/index.md)>  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/copy/#com.sorrowblue.twitlin.utilities.RateLimitStatus.RateLimitContext#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource]/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/copy/#com.sorrowblue.twitlin.utilities.RateLimitStatus.RateLimitContext#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource]/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(rateLimitContext: [RateLimitStatus.RateLimitContext](-rate-limit-context/index.md), resources: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource](-resource/index.md)>): [RateLimitStatus](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/rateLimitContext/#/PointingToDeclaration/"></a>[rateLimitContext](rate-limit-context.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/rateLimitContext/#/PointingToDeclaration/"></a> [common] val [rateLimitContext](rate-limit-context.md): [RateLimitStatus.RateLimitContext](-rate-limit-context/index.md)   <br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/resources/#/PointingToDeclaration/"></a>[resources](resources.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus/resources/#/PointingToDeclaration/"></a> [common] val [resources](resources.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource](-resource/index.md)>   <br>

