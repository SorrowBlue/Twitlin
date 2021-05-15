/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.media.MediaApi
import com.sorrowblue.twitlin.media.MediaCategory
import com.sorrowblue.twitlin.media.MediaResult
import com.sorrowblue.twitlin.media.Subtitle
import com.sorrowblue.twitlin.media.request.MetadataRequest
import com.sorrowblue.twitlin.media.request.SubtitlesRequest
import kotlinx.serialization.builtins.serializer

private const val MEDIA = "https://upload.twitter.com/1.1/media"

internal class MediaApiImpl(private val client: UserClient) : MediaApi {

    override suspend fun upload(
        media: ByteArray?,
        mediaData: String?,
        mediaCategory: MediaCategory,
        additionalOwners: List<String>?
    ): Response<MediaResult> {
        return client.post(
            "$MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            "media" to media?.decodeToString(),
            "media_data" to mediaData,
            "media_category" to mediaCategory.name.lowercase(),
            "additional_owners" to additionalOwners?.joinToString(",")
        )
    }

    override suspend fun uploadInit(
        totalBytes: Int,
        mediaType: String,
        mediaCategory: MediaCategory?,
        additionalOwners: List<String>?,
        shared: Boolean?
    ): Response<MediaResult> {
        return client.post(
            "$MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            "command" to "INIT",
            "total_bytes" to totalBytes,
            "media_type" to mediaType,
            "media_category" to mediaCategory?.name?.lowercase(),
            "additional_owners" to additionalOwners?.joinToString(","),
            "shared" to shared
        )
    }

    override suspend fun uploadAppend(
        mediaId: String,
        media: ByteArray?,
        mediaData: String?,
        segmentIndex: Int
    ): Response<Unit> {
        return client.post(
            "$MEDIA/upload.json",
            Response.serializer(Unit.serializer()),
            "command" to "APPEND",
            "media_id" to mediaId,
            "media" to media?.decodeToString(),
            "media_data" to mediaData,
            "segment_index" to segmentIndex
        )
    }

    override suspend fun uploadFinalize(mediaId: String): Response<MediaResult> {
        return client.post(
            "$MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            "command" to "FINALIZE",
            "media_id" to mediaId
        )
    }

    override suspend fun uploadStatus(mediaId: String): Response<MediaResult> {
        return client.post(
            "$MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            "command" to "STATUS",
            "media_id" to mediaId
        )
    }

    override suspend fun createMetadata(mediaId: String, altText: String): Response<Unit> {
        return client.postJson(
            "$MEDIA/metadata/create.json",
            Response.serializer(Unit.serializer()),
            MetadataRequest(mediaId, MetadataRequest.AltText(altText))
        )
    }

    override suspend fun deleteSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        languageCodes: List<String>
    ): Response<Unit> {
        return client.postJson(
            "$MEDIA/subtitles/delete.json",
            Response.serializer(Unit.serializer()),
            SubtitlesRequest.forDelete(
                mediaId,
                mediaCategory.name.lowercase(),
                languageCodes
            )
        )
    }

    override suspend fun createSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        subtitles: List<Subtitle>
    ): Response<Unit> {
        return client.postJson(
            "$MEDIA/subtitles/create.json",
            Response.serializer(Unit.serializer()),
            SubtitlesRequest.forCreate(mediaId, mediaCategory.name.lowercase(), subtitles)
        )
    }
}
