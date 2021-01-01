//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[uploadStatus](upload-status.md)



# uploadStatus  
[common]  
Content  
abstract suspend fun [uploadStatus](upload-status.md)(mediaId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[MediaResult](../-media-result/index.md)>  
More info  


##  Overview  


The [uploadStatus](upload-status.md) is used to periodically poll for updates of media processing operation. After the [uploadStatus](upload-status.md) response returns [MediaResult.Info.State.SUCCEEDED](../-media-result/-info/-state/-s-u-c-c-e-e-d-e-d/index.md), you can move on to the next step which is usually create Tweet with [MediaResult.mediaId](../-media-result/media-id.md).



##  Response  


The response body contains [MediaResult.processingInfo](../-media-result/processing-info.md) field which provides information about current state of media processing operation. It contains a [MediaResult.Info.state](../-media-result/-info/state.md) field which has transition flow: [MediaResult.Info.State.PENDING](../-media-result/-info/-state/-p-e-n-d-i-n-g/index.md) -> [MediaResult.Info.State.IN_PROGRESS](../-media-result/-info/-state/-i-n_-p-r-o-g-r-e-s-s/index.md) -> [[MediaResult.Info.State.FAILED](../-media-result/-info/-state/-f-a-i-l-e-d/index.md) | [MediaResult.Info.State.SUCCEEDED](../-media-result/-info/-state/-s-u-c-c-e-e-d-e-d/index.md)]. You can not use the [MediaResult.mediaId](../-media-result/media-id.md) to create Tweet or other entities before the state field is set to [MediaResult.Info.State.SUCCEEDED](../-media-result/-info/-state/-s-u-c-c-e-e-d-e-d/index.md).



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadStatus/#kotlin.String/PointingToDeclaration/"></a>mediaId| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadStatus/#kotlin.String/PointingToDeclaration/"></a><br><br>The mediaId returned from the [uploadInit](upload-init.md).<br><br>
  
  



