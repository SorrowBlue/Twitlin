//[twitlin](../../index.md)/[com.sorrowblue.twitlin.users](../index.md)/[AccountApi](index.md)/[updateProfile](update-profile.md)



# updateProfile  
[common]  
Content  
abstract suspend fun [updateProfile](update-profile.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, location: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, profileLinkColor: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, skipStatus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[TwitterUser](../../com.sorrowblue.twitlin.objects/-twitter-user/index.md)>  
More info  


Sets some values that users are able to set under the "Account" tab of their settings page. Only the parameters specified will be updated.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>name| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Full name associated with the profile.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>url| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>URL associated with the profile. Will be prepended with http:// if not present.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>location| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The city or country describing where the user of the account is located. The contents are not normalized or geocoded in any way.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>description| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>A description of the user owning the account.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>profileLinkColor| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Sets a hex value that controls the color scheme of links used on the authenticating user's profile page on twitter.com. This must be a valid hexadecimal value, and may be either three or six characters (ex: F00 or FF0000). This parameter replaces the deprecated (and separate) update_profile_colors API method.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>includeEntities| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The entities node will not be included when set to false.<br><br>
| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a>skipStatus| <a name="com.sorrowblue.twitlin.users/AccountApi/updateProfile/#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.String?#kotlin.Boolean?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>When set to either true, statuses will not be included in the returned user object.<br><br>
  
  



