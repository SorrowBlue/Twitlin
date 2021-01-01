//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[AccountApi](index.md)/[updateProfileBanner](update-profile-banner.md)



# updateProfileBanner  
[common]  
Content  
abstract suspend fun [updateProfileBanner](update-profile-banner.md)(banner: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, offsetLeft: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, offsetTop: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  
More info  


Uploads a profile banner on behalf of the authenticating user. More information about sizing variations can be found in [User Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners) and [UsersApi.profileBanner](../-users-api/profile-banner.md).



Profile banner images are processed asynchronously. The profile_banner_url and its variant sizes will not necessary be available directly after upload.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a>banner| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The Base64-encoded or raw image data being uploaded as the user's new profile banner.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a>width| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The width of the preferred section of the image being uploaded in pixels. Use with height, offsetLeft, and offsetTop to select the desired region of the image to use.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a>height| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The height of the preferred section of the image being uploaded in pixels. Use with width, offsetLeft, and offsetTop to select the desired region of the image to use.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a>offsetLeft| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The number of pixels by which to offset the uploaded image from the left. Use with height, width, and offsetTop to select the desired region of the image to use.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a>offsetTop| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfileBanner/#kotlin.String#kotlin.Int?#kotlin.Int?#kotlin.Int?#kotlin.Int?/PointingToDeclaration/"></a><br><br>The number of pixels by which to offset the uploaded image from the top. Use with height, width, and offsetLeft to select the desired region of the image to use.<br><br>
  
  



