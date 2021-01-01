//[twitlin](../../../index.md)/[com.sorrowblue.twitlin.v2.objects](../../index.md)/[User](../index.md)/[Entities](index.md)



# Entities  
 [common] data class [Entities](index.md)(**url**: [User.Entities.EntitiesUrl](-entities-url/index.md)?, **description**: [User.Entities.Description](-description/index.md)?) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.Description///PointingToDeclaration/"></a>[Description](-description/index.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.Description///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Description](-description/index.md)(**urls**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[User.Entities.Url](-url/index.md)>?, **hashtags**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Hashtag](../../-hashtag/index.md)>?, **mentions**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Mention](../../-mention/index.md)>?, **cashtags**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[CashTag](../../-cash-tag/index.md)>?) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.EntitiesUrl///PointingToDeclaration/"></a>[EntitiesUrl](-entities-url/index.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.EntitiesUrl///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [EntitiesUrl](-entities-url/index.md)(**urls**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[User.Entities.Url](-url/index.md)>) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.Url///PointingToDeclaration/"></a>[Url](-url/index.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities.Url///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [Url](-url/index.md)(**start**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **end**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **url**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **expandedUrl**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **displayUrl**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [User.Entities.EntitiesUrl](-entities-url/index.md)?  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [User.Entities.Description](-description/index.md)?  <br><br><br>
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/copy/#com.sorrowblue.twitlin.v2.objects.User.Entities.EntitiesUrl?#com.sorrowblue.twitlin.v2.objects.User.Entities.Description?/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/copy/#com.sorrowblue.twitlin.v2.objects.User.Entities.EntitiesUrl?#com.sorrowblue.twitlin.v2.objects.User.Entities.Description?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(url: [User.Entities.EntitiesUrl](-entities-url/index.md)? = null, description: [User.Entities.Description](-description/index.md)? = null): [User.Entities](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/description/#/PointingToDeclaration/"></a>[description](description.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/description/#/PointingToDeclaration/"></a> [common] val [description](description.md): [User.Entities.Description](-description/index.md)? = null   <br>
| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/url/#/PointingToDeclaration/"></a>[url](url.md)| <a name="com.sorrowblue.twitlin.v2.objects/User.Entities/url/#/PointingToDeclaration/"></a> [common] val [url](url.md): [User.Entities.EntitiesUrl](-entities-url/index.md)? = null   <br>

