//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[UsersApi](index.md)/[profileBanner](profile-banner.md)



# profileBanner  
[common]  
Content  
abstract suspend fun [profileBanner](profile-banner.md)(userId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, screenName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[ProfileBanner](../-profile-banner/index.md)>  
More info  


Returns a map of the available size variations of the specified user's profile banner. If the user has not uploaded a profile banner, a HTTP 404 will be served instead. This method can be used instead of string manipulation on the profile_banner_url returned in user objects as described in [Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).



The profile banner data available at each size variant's URL is in PNG format.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/UsersApi/profileBanner/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>userId| <a name="com.sorrowblue.twitlin.users/UsersApi/profileBanner/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a><br><br>The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screen name.<br><br>
| <a name="com.sorrowblue.twitlin.users/UsersApi/profileBanner/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>screenName| <a name="com.sorrowblue.twitlin.users/UsersApi/profileBanner/#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a><br><br>The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a user ID.<br><br>
  
  



