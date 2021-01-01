//[twitlin](../../index.md)/[com.sorrowblue.twitlin.v2](../index.md)/[Response](index.md)



# Response  
 [common] sealed class [Response](index.md)<[T](index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>   


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2/Response.Failure///PointingToDeclaration/"></a>[Failure](-failure/index.md)| <a name="com.sorrowblue.twitlin.v2/Response.Failure///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Failure](-failure/index.md)<[T](-failure/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>(**statusCode**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **errors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Error](../-error/index.md)>, **title**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **detail**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **type**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Response](index.md)<[T](-failure/index.md)>   <br><br><br>
| <a name="com.sorrowblue.twitlin.v2/Response.Success///PointingToDeclaration/"></a>[Success](-success/index.md)| <a name="com.sorrowblue.twitlin.v2/Response.Success///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Success](-success/index.md)<[T](-success/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>(**statusCode**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **data**: [T](-success/index.md), **includes**: [Includes](../-includes/index.md)?, **errors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Error](../-error/index.md)>?) : [Response](index.md)<[T](-success/index.md)>   <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2/Response/dataOrNull/#/PointingToDeclaration/"></a>[dataOrNull](data-or-null.md)| <a name="com.sorrowblue.twitlin.v2/Response/dataOrNull/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [dataOrNull](data-or-null.md)(): [T](index.md)?  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2/Response/fold/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Success[TypeParam(bounds=[kotlin.Any])],TypeParam(bounds=[kotlin.Any?])]#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Failure[TypeParam(bounds=[kotlin.Any])],TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[fold](fold.md)| <a name="com.sorrowblue.twitlin.v2/Response/fold/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Success[TypeParam(bounds=[kotlin.Any])],TypeParam(bounds=[kotlin.Any?])]#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Failure[TypeParam(bounds=[kotlin.Any])],TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun <[R](fold.md)> [fold](fold.md)(onSuccess: ([Response.Success](-success/index.md)<[T](index.md)>) -> [R](fold.md), onFailure: ([Response.Failure](-failure/index.md)<[T](index.md)>) -> [R](fold.md)): [R](fold.md)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2/Response/onFailure/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Failure[TypeParam(bounds=[kotlin.Any])],kotlin.Unit]/PointingToDeclaration/"></a>[onFailure](on-failure.md)| <a name="com.sorrowblue.twitlin.v2/Response/onFailure/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Failure[TypeParam(bounds=[kotlin.Any])],kotlin.Unit]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [onFailure](on-failure.md)(action: ([Response.Failure](-failure/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Response](index.md)<[T](index.md)>  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2/Response/onSuccess/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Success[TypeParam(bounds=[kotlin.Any])],kotlin.Unit]/PointingToDeclaration/"></a>[onSuccess](on-success.md)| <a name="com.sorrowblue.twitlin.v2/Response/onSuccess/#kotlin.Function1[com.sorrowblue.twitlin.v2.Response.Success[TypeParam(bounds=[kotlin.Any])],kotlin.Unit]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [onSuccess](on-success.md)(action: ([Response.Success](-success/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Response](index.md)<[T](index.md)>  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| <a name="com.sorrowblue.twitlin.v2/Response.Success///PointingToDeclaration/"></a>[Response](-success/index.md)
| <a name="com.sorrowblue.twitlin.v2/Response.Failure///PointingToDeclaration/"></a>[Response](-failure/index.md)

