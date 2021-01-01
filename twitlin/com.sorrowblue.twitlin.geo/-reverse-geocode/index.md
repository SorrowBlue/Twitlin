//[twitlin](../../index.md)/[com.sorrowblue.twitlin.geo](../index.md)/[ReverseGeocode](index.md)



# ReverseGeocode  
 [common] data class [ReverseGeocode](index.md)(**query**: [ReverseGeocode.Query](-query/index.md), **result**: [ReverseGeocode.Result](-result/index.md)?, **errors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ErrorMessages.Error](../../com.sorrowblue.twitlin.client/-error-messages/-error/index.md)>?) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

TODO

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/ReverseGeocode/#com.sorrowblue.twitlin.geo.ReverseGeocode.Query#com.sorrowblue.twitlin.geo.ReverseGeocode.Result?#kotlin.collections.List[com.sorrowblue.twitlin.client.ErrorMessages.Error]?/PointingToDeclaration/"></a>[ReverseGeocode](-reverse-geocode.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/ReverseGeocode/#com.sorrowblue.twitlin.geo.ReverseGeocode.Query#com.sorrowblue.twitlin.geo.ReverseGeocode.Result?#kotlin.collections.List[com.sorrowblue.twitlin.client.ErrorMessages.Error]?/PointingToDeclaration/"></a> [common] fun [ReverseGeocode](-reverse-geocode.md)(query: [ReverseGeocode.Query](-query/index.md), result: [ReverseGeocode.Result](-result/index.md)? = null, errors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ErrorMessages.Error](../../com.sorrowblue.twitlin.client/-error-messages/-error/index.md)>? = null)   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode.Query///PointingToDeclaration/"></a>[Query](-query/index.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode.Query///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Query](-query/index.md)(**url**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **type**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **params**: [ReverseGeocode.Query.Params](-query/-params/index.md)) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>TODO  <br><br><br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode.Result///PointingToDeclaration/"></a>[Result](-result/index.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode.Result///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Result](-result/index.md)(**places**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Place](../../com.sorrowblue.twitlin.objects/-place/index.md)>) : [JvmSerializable](../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br>More info  <br>TODO  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [ReverseGeocode.Query](-query/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [ReverseGeocode.Result](-result/index.md)?  <br><br><br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component3/#/PointingToDeclaration/"></a>[component3](component3.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/component3/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component3](component3.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ErrorMessages.Error](../../com.sorrowblue.twitlin.client/-error-messages/-error/index.md)>?  <br><br><br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/copy/#com.sorrowblue.twitlin.geo.ReverseGeocode.Query#com.sorrowblue.twitlin.geo.ReverseGeocode.Result?#kotlin.collections.List[com.sorrowblue.twitlin.client.ErrorMessages.Error]?/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/copy/#com.sorrowblue.twitlin.geo.ReverseGeocode.Query#com.sorrowblue.twitlin.geo.ReverseGeocode.Result?#kotlin.collections.List[com.sorrowblue.twitlin.client.ErrorMessages.Error]?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(query: [ReverseGeocode.Query](-query/index.md), result: [ReverseGeocode.Result](-result/index.md)? = null, errors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ErrorMessages.Error](../../com.sorrowblue.twitlin.client/-error-messages/-error/index.md)>? = null): [ReverseGeocode](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/errors/#/PointingToDeclaration/"></a>[errors](errors.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/errors/#/PointingToDeclaration/"></a> [common] val [errors](errors.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ErrorMessages.Error](../../com.sorrowblue.twitlin.client/-error-messages/-error/index.md)>? = null   <br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/query/#/PointingToDeclaration/"></a>[query](query.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/query/#/PointingToDeclaration/"></a> [common] val [query](query.md): [ReverseGeocode.Query](-query/index.md)   <br>
| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/result/#/PointingToDeclaration/"></a>[result](result.md)| <a name="com.sorrowblue.twitlin.geo/ReverseGeocode/result/#/PointingToDeclaration/"></a> [common] val [result](result.md): [ReverseGeocode.Result](-result/index.md)? = null   <br>

