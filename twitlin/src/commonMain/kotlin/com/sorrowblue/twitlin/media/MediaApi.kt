/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.users.AccountApi

/**
 * A media object represents a single photo, video or animated GIF. Media objects are used by many
 * endpoints within the Twitter API, and may be included in Tweets, Direct Messages, user profiles,
 * advertising creatives and elsewhere. Each media object may have multiple display or playback
 * variants, with different resolutions or formats.
 */
public interface MediaApi {

    /**
     * ### Overview
     * Use this endpoint to upload images to Twitter.
     *
     * This endpoint returns a [MediaResult.mediaId] by default and can optionally return a
     * [MediaResult.mediaKey] when a [mediaCategory] is specified. These values are used by Twitter
     * endpoints that accept images.
     *
     * For example, a [MediaResult.mediaId] value can be used to create a Tweet with an attached
     * photo using the [StatusesApi.update] endpoint.
     *
     * All [Ads API endpoints](https://developer.twitter.com/en/docs/ads/) require a
     * [MediaResult.mediaKey]. For example, a [MediaResult.mediaKey] value can be used to create a
     * Draft Tweet with a photo using the [AccountApi.draftTweets] endpoint.
     *
     * ### Usage
     * This is a simple image upload endpoint with a limited set of features. The preferred
     * alternative is the chunked upload endpoint which supports both images and videos, provides
     * better reliability, allows resumption of file uploads, and other important features. In the
     * future, new features will only be supported for the chunked upload endpoint.
     *
     * * See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices)
     * for constraints and requirements on media files.
     * * See the [Uploading Media tutorial](https://developer.twitter.com/en/docs/tutorials/uploading-media)
     * for step-by-step instructions on uploading an image.
     * * Use the [media metadata endpoint](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-metadata-create)
     * to provide image alt text information.
     * * Ensure the POST is a `multipart/form-data` request. Either upload the raw binary ([media]
     * parameter) of the file, or its base64-encoded contents (`media_data` parameter). Use raw
     * binary when possible, because base64 encoding results in larger file sizes.
     * * The response provides a media identifier in the [MediaResult.mediaId] (64-bit integer) and
     * [MediaResult.mediaIdString] (string) fields. Use the [MediaResult.mediaIdString] provided in
     * the API response from JavaScript and other languages that cannot accurately represent a long
     * integer.
     * * The returned [MediaResult.mediaId] and [MediaResult.mediaKey] are only valid for
     * [MediaResult.expiresAfterSecs] seconds. Any attempt to use either after this time period in
     * other endpoints will result in an HTTP 4xx Bad Request.
     * * The  field enables media to be uploaded as user A and then used to create Tweets as user B.
     * * Please note that to upload videos or GIFs ([MediaCategory.TWEET_VIDEO],
     * [MediaCategory.AMPLIFY_VIDEO], and [MediaCategory.TWEET_GIF]), you need to use the
     * [chunked upload end-point](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-init).
     *
     * @param media The raw binary file content being uploaded. Cannot be used with [mediaData].
     * @param mediaData The base64-encoded chunk of media file. It must be <= 5 MB and cannot be
     * used with media. Use raw binary ([media] parameter) when possible.
     * @param mediaCategory The category that represents how the media will be used. This field is
     * required when using the media with the Ads API.
     * @param additionalOwners A comma-separated list of user IDs to set as additional owners
     * allowed to use the returned [MediaResult.mediaId] in Tweets or Cards. Up to `100` additional
     * owners may be specified.
     * @return TODO
     */
    public suspend fun upload(
        media: ByteArray? = null,
        mediaData: String? = null,
        mediaCategory: MediaCategory,
        additionalOwners: List<String>? = null
    ): Response<MediaResult>

    /**
     * ### Overview
     * The [uploadInit] request is used to initiate a file upload session. It returns a
     * [MediaResult.mediaId] which should be used to execute all subsequent requests. The next step
     * after a successful return from [uploadInit] is the [uploadAppend].
     *
     * See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices)
     * for constraints and requirements on media files.
     *
     * ### Response
     * The response provides a media identifier in the [MediaResult.mediaId] (64-bit integer) and
     * [MediaResult.mediaIdString] (string) fields. Use the [MediaResult.mediaIdString] provided in
     * the API response from JavaScript and other languages that cannot accurately represent a long
     * integer.
     *
     * The entire file must be uploaded before [MediaResult.expiresAfterSecs] seconds.
     *
     * The [additionalOwners] field enables media to be uploaded media as user A and then used to
     * create Tweets as user B.
     *
     * @param totalBytes The size of the media being uploaded in bytes.
     * @param mediaType The MIME type of the media being uploaded.
     * @param mediaCategory A string enum value which identifies a media usecase. This identifier is
     * used to enforce usecase specific constraints (e.g. file size, video duration) and enable
     * advanced features.
     * @param additionalOwners A comma-separated list of user IDs to set as additional owners
     * allowed to use the returned [MediaResult.mediaId] in Tweets or Cards. Up to `100` additional
     * owners may be specified.
     * @param shared TODO
     * @return TODO
     */
    public suspend fun uploadInit(
        totalBytes: Int,
        mediaType: String,
        mediaCategory: MediaCategory? = null,
        additionalOwners: List<String>? = null,
        shared: Boolean? = null
    ): Response<MediaResult>

    /**
     * ### Overview
     * The [uploadAppend] is used to upload a chunk (consecutive byte range) of the media file. For
     * example, a 3 MB file could be split into 3 chunks of size 1 MB, and uploaded using 3
     * [uploadAppend] requests. After the entire file is uploaded, the next step is to call the
     * [uploadFinalize].
     *
     * There are a number of advantages of uploading a media file in small chunks:
     *
     * * Improved reliability and success rates under low bandwidth network conditions
     * * Uploads can be paused and resumed
     * * File chunks can be retried individually
     * * Ability to tune chunk sizes to match changing network conditions e.g on cellular clients
     *
     * ### Response
     * A successful response returns HTTP 2xx.
     *
     * @param mediaId The [mediaId] returned from the [uploadInit].
     * @param media The raw binary file content being uploaded. It must be <= 5 MB, and cannot be
     * used with [mediaData].
     * @param mediaData The base64-encoded chunk of media file. It must be <= 5 MB and cannot be
     * used with [media]. Use raw binary ([media] parameter) when possible.
     * @param segmentIndex An ordered index of file chunk. It must be between `0`-`999` inclusive.
     * The first segment has index `0`, second segment has index `1`, and so on.
     * @return TODO
     */
    public suspend fun uploadAppend(
        mediaId: String,
        media: ByteArray? = null,
        mediaData: String? = null,
        segmentIndex: Int
    ): Response<Unit>

    /**
     * ### Overview
     * The [uploadFinalize] should be called after the entire media file is uploaded using
     * [uploadAppend]. If and (only if) the response of the [uploadFinalize] contains a
     * [MediaResult.processingInfo] field, it may also be necessary to use a [uploadStatus] and wait
     * for it to return success before proceeding to Tweet creation.
     *
     * ### Response
     * The response provides a media identifier in the [MediaResult.mediaId] (64-bit integer) and
     * [MediaResult.mediaIdString] (string) fields. Use the [MediaResult.mediaIdString] provided in
     * the API response from JavaScript and other languages that cannot accurately represent a long
     * integer.
     *
     * The returned mediaId is only valid for [MediaResult.expiresAfterSecs] seconds. Any attempt to
     * use mediaId after this time period in other API calls will result in a Bad Request (HTTP 4xx)
     * response.
     *
     * If the response contains a [MediaResult.processingInfo] field, then use the [uploadStatus] to
     * poll for the status of the [uploadFinalize] operation. The async finalize approach is used
     * for cases where media processing requires more time. In future, all video and animated GIF
     * processing will only be supported using async finalize. This behavior is enabled if an upload
     * session was [uploadInit] with a [uploadInit.mediaCategory] parameter, and when then media
     * type is either video or animated GIF.
     *
     * If a [MediaResult.processingInfo] field is NOT returned in the response, then
     * [MediaResult.mediaId] is ready for use in other API endpoints.
     *
     * @param mediaId The media_id returned from the [uploadInit].
     * @return
     */
    public suspend fun uploadFinalize(mediaId: String): Response<MediaResult>

    /**
     * ### Overview
     * The [uploadStatus] is used to periodically poll for updates of media processing operation.
     * After the [uploadStatus] response returns [MediaResult.Info.State.SUCCEEDED], you can move on
     * to the next step which is usually create Tweet with [MediaResult.mediaId].
     *
     * ### Response
     * The response body contains [MediaResult.processingInfo] field which provides information
     * about current state of media processing operation. It contains a [MediaResult.Info.state]
     * field which has transition flow: [MediaResult.Info.State.PENDING] ->
     * [MediaResult.Info.State.IN_PROGRESS] -> [[MediaResult.Info.State.FAILED] |
     * [MediaResult.Info.State.SUCCEEDED]]. You can not use the [MediaResult.mediaId] to create
     * Tweet or other entities before the state field is set to [MediaResult.Info.State.SUCCEEDED].
     *
     * @param mediaId The [mediaId] returned from the [uploadInit].
     * @return TODO
     */
    public suspend fun uploadStatus(mediaId: String): Response<MediaResult>

    /**
     * ### Overview
     * This endpoint can be used to provide additional information about the uploaded
     * [MediaResult.mediaId]. This feature is currently only supported for images and GIFs.
     *
     * The request flow should be:
     * 1. Upload media using either the [upload] or the (preferred) [uploadInit].
     * 1. Call this endpoint to attach additional metadata such as image alt text.
     * 1. Create Tweet with [mediaId](s) attached.
     *
     * ### Response
     * A successful response returns HTTP 2xx.
     *
     * @param mediaId The [mediaId] returned from the [upload] or the [uploadInit].
     * @param altText TODO
     * @return TODO
     */
    public suspend fun createMetadata(mediaId: String, altText: String): Response<Unit>

    /**
     * Use this endpoint to dissociate subtitles from a video and delete the subtitles. You can
     * dissociate subtitles from a video before or after Tweeting.
     *
     * @param mediaId TODO
     * @param mediaCategory TODO
     * @param languageCodes TODO
     * @return TODO
     */
    public suspend fun deleteSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        languageCodes: List<String>
    ): Response<Unit>

    /**
     * Use this endpoint to associate uploaded subtitles to an uploaded video. You can associate
     * subtitles to video before or after Tweeting.
     *
     * Request flow for associating subtitle to video before the video is Tweeted:
     * 1. Upload video using the chunked upload endpoint and get the video media_id.
     * 1. Upload subtitle using the chunked upload endpoint with media category set to “Subtitles”
     * and get the subtitle media_id.
     * 1. Call this endpoint to associate the subtitle to the video.
     * 1. Create Tweet with the video media_id.
     *
     * Request flow for associating subtitle to video after the video is Tweeted:
     * 1. Upload video using the chunked upload endpoint and get the video media_id.
     * 1. Create Tweet with the video media_id.
     * 1. Upload subtitle using the chunked upload endpoint with media category set to SUBTITLES
     * and get the subtitle media_id.
     * 1. Call this endpoint to associate the subtitle to the video.
     *
     * @param mediaId TODO
     * @param mediaCategory TODO
     * @param subtitles TODO
     * @return TODO
     */
    public suspend fun createSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        subtitles: List<Subtitle>
    ): Response<Unit>
}
