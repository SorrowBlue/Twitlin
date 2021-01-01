//[twitlin](../../../index.md)/[com.sorrowblue.twitlin.objects](../../index.md)/[Entities](../index.md)/[Symbol](index.md)



# Symbol  
 [common] data class [Symbol](index.md)(**indices**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>, **text**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [JvmSerializable](../../../com.sorrowblue.twitlin.annotation/-jvm-serializable/index.md)

The [TwitterTweet.entities](../../-twitter-tweet/entities.md) section will contain a [symbols](../symbols.md) array containing an object for every $cashtag included in the Tweet body, and include an empty array if no symbol is present. The PowerTrack $ Operator is used to match on the [text](text.md) attribute. The has:symbols Operator will match if there is at least one item in the array.

   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/Symbol/#kotlin.collections.List[kotlin.Int]#kotlin.String/PointingToDeclaration/"></a>[Symbol](-symbol.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/Symbol/#kotlin.collections.List[kotlin.Int]#kotlin.String/PointingToDeclaration/"></a> [common] fun [Symbol](-symbol.md)(indices: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>, text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/component1/#/PointingToDeclaration/"></a>[component1](component1.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/component1/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component1](component1.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/component2/#/PointingToDeclaration/"></a>[component2](component2.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/component2/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/copy/#kotlin.collections.List[kotlin.Int]#kotlin.String/PointingToDeclaration/"></a>[copy](copy.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/copy/#kotlin.collections.List[kotlin.Int]#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [copy](copy.md)(indices: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>, text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Entities.Symbol](index.md)  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator override fun [equals](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [hashCode](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toString](../../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/indices/#/PointingToDeclaration/"></a>[indices](indices.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/indices/#/PointingToDeclaration/"></a> [common] val [indices](indices.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>An array of integers indicating the offsets within the Tweet text where the symbol/cashtag begins and ends.   <br>
| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/text/#/PointingToDeclaration/"></a>[text](text.md)| <a name="com.sorrowblue.twitlin.objects/Entities.Symbol/text/#/PointingToDeclaration/"></a> [common] val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)Name of the cashhtag, minus the leading ‘$’ character.   <br>

