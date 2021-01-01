//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[uploadFinalize](upload-finalize.md)



# uploadFinalize  
[common]  
Content  
abstract suspend fun [uploadFinalize](upload-finalize.md)(mediaId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[MediaResult](../-media-result/index.md)>  
More info  


##  Overview  


The [uploadFinalize](upload-finalize.md) should be called after the entire media file is uploaded using [uploadAppend](upload-append.md). If and (only if) the response of the [uploadFinalize](upload-finalize.md) contains a [MediaResult.processingInfo](../-media-result/processing-info.md) field, it may also be necessary to use a [uploadStatus](upload-status.md) and wait for it to return success before proceeding to Tweet creation.



##  Response  


The response provides a media identifier in the [MediaResult.mediaId](../-media-result/media-id.md) (64-bit integer) and [MediaResult.mediaIdString](../-media-result/media-id-string.md) (string) fields. Use the [MediaResult.mediaIdString](../-media-result/media-id-string.md) provided in the API response from JavaScript and other languages that cannot accurately represent a long integer.



The returned mediaId is only valid for [MediaResult.expiresAfterSecs](../-media-result/expires-after-secs.md) seconds. Any attempt to use mediaId after this time period in other API calls will result in a Bad Request (HTTP 4xx) response.



If the response contains a [MediaResult.processingInfo](../-media-result/processing-info.md) field, then use the [uploadStatus](upload-status.md) to poll for the status of the [uploadFinalize](upload-finalize.md) operation. The async finalize approach is used for cases where media processing requires more time. In future, all video and animated GIF processing will only be supported using async finalize. This behavior is enabled if an upload session was [uploadInit](upload-init.md) with a uploadInit.mediaCategory parameter, and when then media type is either video or animated GIF.



If a [MediaResult.processingInfo](../-media-result/processing-info.md) field is NOT returned in the response, then [MediaResult.mediaId](../-media-result/media-id.md) is ready for use in other API endpoints.



#### Return  


## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadFinalize/#kotlin.String/PointingToDeclaration/"></a>mediaId| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadFinalize/#kotlin.String/PointingToDeclaration/"></a><br><br>The media_id returned from the [uploadInit](upload-init.md).<br><br>
  
  



