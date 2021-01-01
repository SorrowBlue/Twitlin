//[twitlin](../../index.md)/[com.sorrowblue.twitlin.v2.tweets](../index.md)/[TweetsApi](index.md)/[hidden](hidden.md)



# hidden  
[common]  
Content  
abstract suspend fun [hidden](hidden.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), isHidden: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [Response](../../com.sorrowblue.twitlin.v2/-response/index.md)<[Hidden](../../com.sorrowblue.twitlin.v2.objects/-hidden/index.md)>  
More info  


Hides or unhides a reply to a Tweet.



#### Return  


true if the reply is visible, false if hidden.



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/hidden/#kotlin.String#kotlin.Boolean/PointingToDeclaration/"></a>id| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/hidden/#kotlin.String#kotlin.Boolean/PointingToDeclaration/"></a><br><br>Unique identifier of the Tweet to hide or unhide. The Tweet must belong to a conversation initiated by the authenticating user.<br><br>
| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/hidden/#kotlin.String#kotlin.Boolean/PointingToDeclaration/"></a>isHidden| <a name="com.sorrowblue.twitlin.v2.tweets/TweetsApi/hidden/#kotlin.String#kotlin.Boolean/PointingToDeclaration/"></a><br><br>Indicates the action to perform. Specify true to hide the Tweet, false to unhide. Trying to hide a Tweet that's already hidden (or unhide a Tweet that is not hidden) will result in a successful call. If isHidden is not specified, it will be hidden.<br><br>
  
  



