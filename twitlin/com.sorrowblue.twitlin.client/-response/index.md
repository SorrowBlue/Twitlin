//[twitlin](../../index.md)/[com.sorrowblue.twitlin.client](../index.md)/[Response](index.md)



# Response  
 [common] sealed class [Response](index.md)<[T](index.md)>

TODO

   


## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.client/Response///PointingToDeclaration/"></a>T| <a name="com.sorrowblue.twitlin.client/Response///PointingToDeclaration/"></a><br><br>TODO<br><br>
  


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.client/Response.Error///PointingToDeclaration/"></a>[Error](-error/index.md)| <a name="com.sorrowblue.twitlin.client/Response.Error///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Error](-error/index.md)<[T](-error/index.md)>(**errorMessages**: [ErrorMessages](../-error-messages/index.md)) : [Response](index.md)<[T](-error/index.md)>   <br>More info  <br>TODO  <br><br><br>
| <a name="com.sorrowblue.twitlin.client/Response.Success///PointingToDeclaration/"></a>[Success](-success/index.md)| <a name="com.sorrowblue.twitlin.client/Response.Success///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Success](-success/index.md)<[T](-success/index.md)>(**value**: [T](-success/index.md), **statusCode**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [Response](index.md)<[T](-success/index.md)>   <br>More info  <br>TODO  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.client/Response/fold/#kotlin.Function1[com.sorrowblue.twitlin.client.Response.Success[TypeParam(bounds=[kotlin.Any?])],TypeParam(bounds=[kotlin.Any?])]#kotlin.Function1[com.sorrowblue.twitlin.client.Response.Error[TypeParam(bounds=[kotlin.Any?])],TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[fold](fold.md)| <a name="com.sorrowblue.twitlin.client/Response/fold/#kotlin.Function1[com.sorrowblue.twitlin.client.Response.Success[TypeParam(bounds=[kotlin.Any?])],TypeParam(bounds=[kotlin.Any?])]#kotlin.Function1[com.sorrowblue.twitlin.client.Response.Error[TypeParam(bounds=[kotlin.Any?])],TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun <[R](fold.md)> [fold](fold.md)(onSuccess: ([Response.Success](-success/index.md)<[T](index.md)>) -> [R](fold.md), onError: ([Response.Error](-error/index.md)<[T](index.md)>) -> [R](fold.md)): [R](fold.md)  <br>More info  <br>TODO  <br><br><br>
| <a name="com.sorrowblue.twitlin.client/Response/getOrNull/#/PointingToDeclaration/"></a>[getOrNull](get-or-null.md)| <a name="com.sorrowblue.twitlin.client/Response/getOrNull/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [getOrNull](get-or-null.md)(): [T](index.md)?  <br>More info  <br>TODO  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.client/Response/onError/#kotlin.Function1[com.sorrowblue.twitlin.client.ErrorMessages,kotlin.Unit]/PointingToDeclaration/"></a>[onError](on-error.md)| <a name="com.sorrowblue.twitlin.client/Response/onError/#kotlin.Function1[com.sorrowblue.twitlin.client.ErrorMessages,kotlin.Unit]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [onError](on-error.md)(action: ([ErrorMessages](../-error-messages/index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Response](index.md)<[T](index.md)>  <br>More info  <br>TODO  <br><br><br>
| <a name="com.sorrowblue.twitlin.client/Response/onSuccess/#kotlin.Function1[TypeParam(bounds=[kotlin.Any?]),kotlin.Unit]/PointingToDeclaration/"></a>[onSuccess](on-success.md)| <a name="com.sorrowblue.twitlin.client/Response/onSuccess/#kotlin.Function1[TypeParam(bounds=[kotlin.Any?]),kotlin.Unit]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [onSuccess](on-success.md)(action: ([T](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Response](index.md)<[T](index.md)>  <br>More info  <br>TODO  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| <a name="com.sorrowblue.twitlin.client/Response.Success///PointingToDeclaration/"></a>[Response](-success/index.md)
| <a name="com.sorrowblue.twitlin.client/Response.Error///PointingToDeclaration/"></a>[Response](-error/index.md)

