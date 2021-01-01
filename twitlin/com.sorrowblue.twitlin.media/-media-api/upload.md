//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[upload](upload.md)



# upload  
[common]  
Content  
abstract suspend fun [upload](upload.md)(media: [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)? = null, mediaData: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, mediaCategory: [MediaCategory](../-media-category/index.md), additionalOwners: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[MediaResult](../-media-result/index.md)>  
More info  


##  Overview  


Use this endpoint to upload images to Twitter.



This endpoint returns a [MediaResult.mediaId](../-media-result/media-id.md) by default and can optionally return a [MediaResult.mediaKey](../-media-result/media-key.md) when a mediaCategory is specified. These values are used by Twitter endpoints that accept images.



For example, a [MediaResult.mediaId](../-media-result/media-id.md) value can be used to create a Tweet with an attached photo using the [StatusesApi.update](../../com.sorrowblue.twitlin.tweets.statuses/-statuses-api/update.md) endpoint.



All [Ads API endpoints](https://developer.twitter.com/en/docs/ads/) require a [MediaResult.mediaKey](../-media-result/media-key.md). For example, a [MediaResult.mediaKey](../-media-result/media-key.md) value can be used to create a Draft Tweet with a photo using the AccountApi.draftTweets endpoint.



##  Usage  


This is a simple image upload endpoint with a limited set of features. The preferred alternative is the chunked upload endpoint which supports both images and videos, provides better reliability, allows resumption of file uploads, and other important features. In the future, new features will only be supported for the chunked upload endpoint.

<ul><li>See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices) for constraints and requirements on media files.</li><li>See the [Uploading Media tutorial](https://developer.twitter.com/en/docs/tutorials/uploading-media) for step-by-step instructions on uploading an image.</li><li>Use the [media metadata endpoint](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-metadata-create) to provide image alt text information.</li><li>Ensure the POST is a multipart/form-data request. Either upload the raw binary (media parameter) of the file, or its base64-encoded contents (media_data parameter). Use raw binary when possible, because base64 encoding results in larger file sizes.</li><li>The response provides a media identifier in the [MediaResult.mediaId](../-media-result/media-id.md) (64-bit integer) and [MediaResult.mediaIdString](../-media-result/media-id-string.md) (string) fields. Use the [MediaResult.mediaIdString](../-media-result/media-id-string.md) provided in the API response from JavaScript and other languages that cannot accurately represent a long integer.</li><li>The returned [MediaResult.mediaId](../-media-result/media-id.md) and [MediaResult.mediaKey](../-media-result/media-key.md) are only valid for [MediaResult.expiresAfterSecs](../-media-result/expires-after-secs.md) seconds. Any attempt to use either after this time period in other endpoints will result in an HTTP 4xx Bad Request.</li><li>The  field enables media to be uploaded as user A and then used to create Tweets as user B.</li><li>Please note that to upload videos or GIFs ([MediaCategory.TWEET_VIDEO](../-media-category/-t-w-e-e-t_-v-i-d-e-o/index.md), [MediaCategory.AMPLIFY_VIDEO](../-media-category/-a-m-p-l-i-f-y_-v-i-d-e-o/index.md), and [MediaCategory.TWEET_GIF](../-media-category/-t-w-e-e-t_-g-i-f/index.md)), you need to use the [chunked upload end-point](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-init).</li></ul>

#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>media| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>The raw binary file content being uploaded. Cannot be used with mediaData.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>mediaData| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>The base64-encoded chunk of media file. It must be <= 5 MB and cannot be used with media. Use raw binary (media parameter) when possible.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>mediaCategory| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>The category that represents how the media will be used. This field is required when using the media with the Ads API.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>additionalOwners| <a name="com.sorrowblue.twitlin.media/MediaApi/upload/#kotlin.ByteArray?#kotlin.String?#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a><br><br>A comma-separated list of user IDs to set as additional owners allowed to use the returned [MediaResult.mediaId](../-media-result/media-id.md) in Tweets or Cards. Up to 100 additional owners may be specified.<br><br>
  
  



