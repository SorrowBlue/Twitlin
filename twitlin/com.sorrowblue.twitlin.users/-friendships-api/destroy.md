//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendshipsApi](index.md)/[destroy](destroy.md)



# destroy  
[common]  
Content  
abstract suspend fun [destroy](destroy.md)(screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Allows the authenticating user to unfollow the user specified in the ID parameter.



Returns the unfollowed user when successful. Returns a string describing the failure condition when unsuccessful.



Actions taken in this method are asynchronous. Changes will be eventually consistent.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/destroy/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/destroy/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a><br><br>The screen name of the user to unfollow.<br><br>
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/destroy/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/destroy/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a><br><br>The ID of the user to unfollow.<br><br>
  
  



