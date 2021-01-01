/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.media.impl

import com.sorrowblue.twitlin.client.Empty
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.media.MediaApi
import com.sorrowblue.twitlin.media.MediaCategory
import com.sorrowblue.twitlin.media.MediaResult
import com.sorrowblue.twitlin.media.Subtitle
import com.sorrowblue.twitlin.media.request.MetadataRequest
import com.sorrowblue.twitlin.media.request.SubtitlesRequest

private const val MEDIA = "https://upload.twitter.com/1.1/media"

internal class MediaApiImpl(private val client: TwitlinClient) : MediaApi {

    override suspend fun upload(
        media: ByteArray?,
        mediaData: String?,
        mediaCategory: MediaCategory,
        additionalOwners: List<String>?
    ): Response<MediaResult> = client.postMultiPartFormData(
        "$MEDIA/upload.json",
        "media" to media?.decodeToString(),
        "media_data" to mediaData,
        "media_category" to mediaCategory.name.toLowerCase(),
        "additional_owners" to additionalOwners?.joinToString(",")
    )

    override suspend fun uploadInit(
        totalBytes: Int,
        mediaType: String,
        mediaCategory: MediaCategory?,
        additionalOwners: List<String>?,
        shared: Boolean?
    ): Response<MediaResult> = client.post(
        "$MEDIA/upload.json",
        "command" to "INIT",
        "total_bytes" to totalBytes,
        "media_type" to mediaType,
        "media_category" to mediaCategory?.name?.toLowerCase(),
        "additional_owners" to additionalOwners?.joinToString(","),
        "shared" to shared
    )

    override suspend fun uploadAppend(
        mediaId: String,
        media: ByteArray?,
        mediaData: String?,
        segmentIndex: Int
    ): Response<Empty> = client.post(
        "$MEDIA/upload.json",
        "command" to "APPEND",
        "media_id" to mediaId,
        "media" to media?.decodeToString(),
        "media_data" to mediaData,
        "segment_index" to segmentIndex
    )

    override suspend fun uploadFinalize(mediaId: String): Response<MediaResult> =
        client.post("$MEDIA/upload.json", "command" to "FINALIZE", "media_id" to mediaId)

    override suspend fun uploadStatus(mediaId: String): Response<MediaResult> =
        client.post("$MEDIA/upload.json", "command" to "STATUS", "media_id" to mediaId)

    override suspend fun createMetadata(mediaId: String, altText: String): Response<Unit> =
        client.postJson(
            "$MEDIA/metadata/create.json",
            clazz = MetadataRequest(mediaId, MetadataRequest.AltText(altText))
        )

    override suspend fun deleteSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        languageCodes: List<String>
    ): Response<Unit> = client.postJson(
        "$MEDIA/subtitles/delete.json",
        clazz = SubtitlesRequest.forDelete(mediaId, mediaCategory.name.toLowerCase(), languageCodes)
    )

    override suspend fun createSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        subtitles: List<Subtitle>
    ): Response<Unit> = client.postJson(
        "$MEDIA/subtitles/create.json",
        clazz = SubtitlesRequest.forCreate(mediaId, mediaCategory.name.toLowerCase(), subtitles)
    )
}
