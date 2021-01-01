//[twitlin](../../index.md)/[com.sorrowblue.twitlin.utilities](../index.md)/[HelpApi](index.md)



# HelpApi  
 [common] interface [HelpApi](index.md)

##  Get Twitter configuration details  


A number of Twitter API parameters and defaults are fixed, including twitter.com slugs which are not usernames, maximum photo resolutions, the length of t.co shortened URLs, and more. Applications should request this endpoint when they are loaded, but no more than once a day to check for current defaults.



##  Get Twitter supported languages  


The standard Twitter API supports a number of different languages. This endpoint the list of languages supported by Twitter, along with the language code(s) supported by Twitter.

   


## Functions  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.utilities/HelpApi/configuration/#/PointingToDeclaration/"></a>[configuration](configuration.md)| <a name="com.sorrowblue.twitlin.utilities/HelpApi/configuration/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [configuration](configuration.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Configuration](../-configuration/index.md)>  <br>More info  <br>Returns the current configuration used by Twitter including twitter.com slugs which are not usernames, maximum photo resolutions, and t.co shortened URL length.  <br><br><br>
| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open operator fun [equals](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2Fequals%2F%23kotlin.Any%3F%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/hashCode/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [hashCode](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FhashCode%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| <a name="com.sorrowblue.twitlin.utilities/HelpApi/languages/#/PointingToDeclaration/"></a>[languages](languages.md)| <a name="com.sorrowblue.twitlin.utilities/HelpApi/languages/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun [languages](languages.md)(): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Language](../-language/index.md)>>  <br>More info  <br>Returns the list of languages supported by Twitter along with the language code supported by Twitter.  <br><br><br>
| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)| <a name="kotlin/Any/toString/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open fun [toString](../../com.sorrowblue.twitlin.v2.users/-users-api/-expansion/-companion/index.md#%5Bkotlin%2FAny%2FtoString%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F1930806739)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

