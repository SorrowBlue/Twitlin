//[twitlin](../../index.md)/[com.sorrowblue.twitlin.v2.tweets](../index.md)/[TweetsApi](index.md)/[searchStreamRules](search-stream-rules.md)



# searchStreamRules  
[common]  
Content  
abstract suspend fun [searchStreamRules](search-stream-rules.md)(ids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null): [Response](../../com.sorrowblue.twitlin.v2/-response/index.md)<[SearchStreamRule](../../com.sorrowblue.twitlin.v2.objects/-search-stream-rule/index.md)>  
More info  


Return a list of rules currently active on the streaming endpoint, either as a list or individually.



#### Return  


List of rules currently active on the streaming endpoint.



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/searchStreamRules/#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>ids| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/searchStreamRules/#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>List of rule IDs. If omitted, all rules are returned.<br><br>
  
  



