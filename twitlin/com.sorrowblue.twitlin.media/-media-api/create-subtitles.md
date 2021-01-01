//[twitlin](../../index.md)/[com.sorrowblue.twitlin.media](../index.md)/[MediaApi](index.md)/[createSubtitles](create-subtitles.md)



# createSubtitles  
[common]  
Content  
abstract suspend fun [createSubtitles](create-subtitles.md)(mediaId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), mediaCategory: [MediaCategory](../-media-category/index.md), subtitles: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Subtitle](../-subtitle/index.md)>): [Response](../../com.sorrowblue.twitlin.client/-response/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  
More info  


Use this endpoint to associate uploaded subtitles to an uploaded video. You can associate subtitles to video before or after Tweeting.



Request flow for associating subtitle to video before the video is Tweeted:

<ol><li>Upload video using the chunked upload endpoint and get the video media_id.</li><li>Upload subtitle using the chunked upload endpoint with media category set to “Subtitles” and get the subtitle media_id.</li><li>Call this endpoint to associate the subtitle to the video.</li><li>Create Tweet with the video media_id.</li></ol>

Request flow for associating subtitle to video after the video is Tweeted:

<ol><li>Upload video using the chunked upload endpoint and get the video media_id.</li><li>Create Tweet with the video media_id.</li><li>Upload subtitle using the chunked upload endpoint with media category set to SUBTITLES and get the subtitle media_id.</li><li>Call this endpoint to associate the subtitle to the video.</li></ol>

#### Return  


TODO



## Parameters  
  
common  
  
|  Name|  Summary| 
|---|---|
| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a>mediaId| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a><br><br>TODO<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a>mediaCategory| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a><br><br>TODO<br><br>
| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a>subtitles| <a name="com.sorrowblue.twitlin.media/MediaApi/createSubtitles/#kotlin.String#com.sorrowblue.twitlin.media.MediaCategory#kotlin.collections.List[com.sorrowblue.twitlin.media.Subtitle]/PointingToDeclaration/"></a><br><br>TODO<br><br>
  
  



