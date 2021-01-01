//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[uploadInit](upload-init.md)



# uploadInit  
[common]  
Content  
abstract suspend fun [uploadInit](upload-init.md)(totalBytes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), mediaType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mediaCategory: [MediaCategory](../-media-category/index.md)? = null, additionalOwners: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, shared: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[MediaResult](../-media-result/index.md)>  
More info  


##  Overview  


The [uploadInit](upload-init.md) request is used to initiate a file upload session. It returns a [MediaResult.mediaId](../-media-result/media-id.md) which should be used to execute all subsequent requests. The next step after a successful return from [uploadInit](upload-init.md) is the [uploadAppend](upload-append.md).



See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices) for constraints and requirements on media files.



##  Response  


The response provides a media identifier in the [MediaResult.mediaId](../-media-result/media-id.md) (64-bit integer) and [MediaResult.mediaIdString](../-media-result/media-id-string.md) (string) fields. Use the [MediaResult.mediaIdString](../-media-result/media-id-string.md) provided in the API response from JavaScript and other languages that cannot accurately represent a long integer.



The entire file must be uploaded before [MediaResult.expiresAfterSecs](../-media-result/expires-after-secs.md) seconds.



The additionalOwners field enables media to be uploaded media as user A and then used to create Tweets as user B.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a>totalBytes| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The size of the media being uploaded in bytes.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a>mediaType| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>The MIME type of the media being uploaded.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a>mediaCategory| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>A string enum value which identifies a media usecase. This identifier is used to enforce usecase specific constraints (e.g. file size, video duration) and enable advanced features.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a>additionalOwners| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>A comma-separated list of user IDs to set as additional owners allowed to use the returned [MediaResult.mediaId](../-media-result/media-id.md) in Tweets or Cards. Up to 100 additional owners may be specified.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a>shared| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadInit/#kotlin.Int#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory?#kotlin.collections.List[kotlin.String]?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>TODO<br><br>
  
  



