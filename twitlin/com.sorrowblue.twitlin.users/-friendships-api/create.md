//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[FriendshipsApi](index.md)/[create](create.md)



# create  
[common]  
Content  
abstract suspend fun [create](create.md)(screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, follow: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Allows the authenticating user to follow (friend) the user specified in the ID parameter.



Returns the followed user when successful. Returns a string describing the failure condition when unsuccessful. If the user is already friends with the user a HTTP 403 may be returned, though for performance reasons this method may also return a HTTP 200 OK message even if the follow relationship already exists.



Actions taken in this method are asynchronous. Changes will be eventually consistent.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>
| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>follow| <a name="com.sorrowblue.twitlin.users/FriendshipsApi/create/#kotlin.String?#kotlin.String?#kotlin.Boolean?/PointingToDeclaration/"></a>
  
  



