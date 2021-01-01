//[twitlin](../../index.md)/[com.sorrowblue.twitlin.v2.tweets](../index.md)/[TweetsApi](index.md)/[addSearchStreamRules](add-search-stream-rules.md)



# addSearchStreamRules  
[common]  
Content  
abstract suspend fun [addSearchStreamRules](add-search-stream-rules.md)(rules: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AddSearchStreamRule](../../com.sorrowblue.twitlin.v2.objects/-add-search-stream-rule/index.md)>, dryRun: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.v2/-response/index.md)<[AddSearchStreamRuleResult](../../com.sorrowblue.twitlin.v2.objects/-add-search-stream-rule-result/index.md)>  
More info  


Add or delete rules to your stream. To create one or more rules, specify [addSearchStreamRules](add-search-stream-rules.md) with an array of StreamRules.



#### Return  


## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/addSearchStreamRules/#kotlin.collections.List[com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule]#kotlin.Boolean?/PointingToDeclaration/"></a>rules| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/addSearchStreamRules/#kotlin.collections.List[com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule]#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Specifies the operation you want to perform on the rules<br><br>
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/addSearchStreamRules/#kotlin.collections.List[com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule]#kotlin.Boolean?/PointingToDeclaration/"></a>dryRun| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/addSearchStreamRules/#kotlin.collections.List[com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule]#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Set to true to test a the syntax of your rule without submitting it. This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.<br><br>
  
  



