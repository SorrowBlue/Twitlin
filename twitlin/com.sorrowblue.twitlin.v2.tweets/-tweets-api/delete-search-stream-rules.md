//[twitlin](../../index.md)/[com.sorrowblue.twitlin.v2.tweets](../index.md)/[TweetsApi](index.md)/[deleteSearchStreamRules](delete-search-stream-rules.md)



# deleteSearchStreamRules  
[common]  
Content  
abstract suspend fun [deleteSearchStreamRules](delete-search-stream-rules.md)(ids: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>, dryRun: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.v2/-response/index.md)<[DeleteSearchStreamRuleResult](../../com.sorrowblue.twitlin.v2.objects/-delete-search-stream-rule-result/index.md)>  
More info  


Add or delete rules to your stream. To delete one or more rules, specify ids as an array of a list of existing rule IDs.



#### Return  


## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/deleteSearchStreamRules/#kotlin.collections.List[kotlin.String]#kotlin.Boolean?/PointingToDeclaration/"></a>ids| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/deleteSearchStreamRules/#kotlin.collections.List[kotlin.String]#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>List of rule IDs, each one representing a rule already active in your stream.<br><br>
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/deleteSearchStreamRules/#kotlin.collections.List[kotlin.String]#kotlin.Boolean?/PointingToDeclaration/"></a>dryRun| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/deleteSearchStreamRules/#kotlin.collections.List[kotlin.String]#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Set to true to test a the syntax of your rule without submitting it. This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.<br><br>
  
  



