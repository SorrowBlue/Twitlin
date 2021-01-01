//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[createMetadata](create-metadata.md)



# createMetadata  
[common]  
Content  
abstract suspend fun [createMetadata](create-metadata.md)(mediaId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), altText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  
More info  


##  Overview  


This endpoint can be used to provide additional information about the uploaded [MediaResult.mediaId](../-media-result/media-id.md). This feature is currently only supported for images and GIFs.



The request flow should be:

<ol><li>Upload media using either the [upload](upload.md) or the (preferred) [uploadInit](upload-init.md).</li><li>Call this endpoint to attach additional metadata such as image alt text.</li><li>Create Tweet with s attached.</li></ol>

##  Response  


A successful response returns HTTP 2xx.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/createMetadata/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>mediaId| <a name="com.sorrowblue.twitlin.media/MediaApi/createMetadata/#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>The mediaId returned from the [upload](upload.md) or the [uploadInit](upload-init.md).<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/createMetadata/#kotlin.String#kotlin.String/PointingToDeclaration/"></a>altText| <a name="com.sorrowblue.twitlin.media/MediaApi/createMetadata/#kotlin.String#kotlin.String/PointingToDeclaration/"></a><br><br>TODO<br><br>
  
  



