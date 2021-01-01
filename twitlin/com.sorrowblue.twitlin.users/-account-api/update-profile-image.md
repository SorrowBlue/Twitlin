//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[AccountApi](index.md)/[updateProfileImage](update-profile-image.md)



# updateProfileImage  
[common]  
Content  
abstract suspend fun [updateProfileImage](update-profile-image.md)(image: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Updates the authenticating user's profile image. Note that this method expects raw multipart data, not a URL to an image.



This method asynchronously processes the uploaded file before updating the user's profile image URL. You can either update your local cache the next time you request the user's information, or, at least 5 seconds after uploading the image, ask for the updated URL using [UsersApi.show](../-users-api/show.md).



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>image| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The avatar image for the profile, base64-encoded. Must be a valid GIF, JPG, or PNG image of less than 700 kilobytes in size. Images with width larger than 400 pixels will be scaled down. Animated GIFs will be converted to a static GIF of the first frame, removing the animation.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileImage/#kotlin.String#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true , t or 1 statuses will not be included in the returned user objects.<br><br>
  
  



