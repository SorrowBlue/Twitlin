//[twitlin](../../../index.md)/[com.sorrowblue.twitlin.utilities](../../index.md)/[RateLimitStatus](../index.md)/[Resource](index.md)



# Resource  
 [common] data class [Resource](index.md)(**family**: [ResourceFamily](../../-resource-family/index.md), **info**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-info/index.md)>) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

TODO

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/Resource/#com.sorrowblue.twitlin.utilities.ResourceFamily#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource.Info]/PointingToDeclaration/"></a>[Resource](-resource.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/Resource/#com.sorrowblue.twitlin.utilities.ResourceFamily#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource.Info]/PointingToDeclaration/"></a> [common] fun [Resource](-resource.md)(family: [ResourceFamily](../../-resource-family/index.md), info: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-info/index.md)>)   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource.Info///PointingToDeclaration/"></a>[Info](-info/index.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource.Info///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Info](-info/index.md)(**path**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **limit**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **remaining**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **reset**: LocalDateTime) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>TODO  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [ResourceFamily](../../-resource-family/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-info/index.md)>  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/copy/#com.sorrowblue.twitlin.utilities.ResourceFamily#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource.Info]/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/copy/#com.sorrowblue.twitlin.utilities.ResourceFamily#kotlin.collections.List[com.sorrowblue.twitlin.utilities.RateLimitStatus.Resource.Info]/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(family: [ResourceFamily](../../-resource-family/index.md), info: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-info/index.md)>): [RateLimitStatus.Resource](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/family/#/PointingToDeclaration/"></a>[family](family.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/family/#/PointingToDeclaration/"></a> [common] val [family](family.md): [ResourceFamily](../../-resource-family/index.md)   <br>
| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/info/#/PointingToDeclaration/"></a>[info](info.md)| <a name="com.sorrowblue.twitlin.utilities/RateLimitStatus.Resource/info/#/PointingToDeclaration/"></a> [common] val [info](info.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[RateLimitStatus.Resource.Info](-info/index.md)>   <br>

