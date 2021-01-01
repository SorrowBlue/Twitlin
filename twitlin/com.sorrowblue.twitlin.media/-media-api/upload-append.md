//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[uploadAppend](upload-append.md)



# uploadAppend  
[common]  
Content  
abstract suspend fun [uploadAppend](upload-append.md)(mediaId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), media: [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)? = null, mediaData: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, segmentIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Empty](../../com.sorrowblue.twitlin.client/-empty/index.md)>  
More info  


##  Overview  


The [uploadAppend](upload-append.md) is used to upload a chunk (consecutive byte range) of the media file. For example, a 3 MB file could be split into 3 chunks of size 1 MB, and uploaded using 3 [uploadAppend](upload-append.md) requests. After the entire file is uploaded, the next step is to call the [uploadFinalize](upload-finalize.md).



There are a number of advantages of uploading a media file in small chunks:

<ul><li>Improved reliability and success rates under low bandwidth network conditions</li><li>Uploads can be paused and resumed</li><li>File chunks can be retried individually</li><li>Ability to tune chunk sizes to match changing network conditions e.g on cellular clients</li></ul>

##  Response  


A successful response returns HTTP 2xx.



#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a>mediaId| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a><br><br>The mediaId returned from the [uploadInit](upload-init.md).<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a>media| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a><br><br>The raw binary file content being uploaded. It must be <= 5 MB, and cannot be used with mediaData.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a>mediaData| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a><br><br>The base64-encoded chunk of media file. It must be <= 5 MB and cannot be used with media. Use raw binary (media parameter) when possible.<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a>segmentIndex| <a name="com.sorrowblue.twitlin.media/MediaApi/uploadAppend/#kotlin.String#kotlin.ByteArray?#kotlin.String?#kotlin.Int/PointingToDeclaration/"></a><br><br>An ordered index of file chunk. It must be between 0-999 inclusive. The first segment has index 0, second segment has index 1, and so on.<br><br>
  
  



