package com.sorrowblue.twitlin.api.media.impl

import com.sorrowblue.twitlin.api.API_MEDIA
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.media.MediaApi
import com.sorrowblue.twitlin.api.media.MediaCategory
import com.sorrowblue.twitlin.api.media.MediaResult
import com.sorrowblue.twitlin.api.media.Subtitle
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.MediaId
import io.ktor.client.request.forms.formData
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.builtins.serializer

internal class MediaApiImpl(private val client: TwitterClient) : MediaApi {

    override suspend fun upload(
        media: ByteArray?,
        mediaData: String?,
        mediaCategory: MediaCategory,
        additionalOwners: List<String>?
    ): Response<MediaResult> {
        return client.submitForm(
            "$API_MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            Parameters.build {
                media?.let { append("media", it.decodeToString()) }
                mediaData?.let { append("media_data", it) }
                append("media_category", mediaCategory.name.lowercase())
                additionalOwners?.let { append("additional_owners", it.joinToString(",")) }
            }
        )
    }

    override suspend fun uploadInit(
        totalBytes: Int,
        mediaType: String,
        mediaCategory: MediaCategory?,
        additionalOwners: List<String>?,
        shared: Boolean?
    ): Response<MediaResult> {
        return client.submitFormWithBinaryData(
            "$API_MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            formData {
                append("command", "INIT")
                append("total_bytes", totalBytes)
                append("media_type", mediaType)
                mediaCategory?.let { append("media_category", it.name.lowercase()) }
                additionalOwners?.let { append("additional_owners", it.joinToString(",")) }
                shared?.let { append("shared", it.toString()) }
            }
        )
    }

    override suspend fun uploadAppend(
        mediaId: MediaId,
        media: ByteArray?,
        mediaData: String?,
        segmentIndex: Int
    ): Response<Unit> {
        return client.submitFormWithBinaryData(
            "$API_MEDIA/upload.json",
            Response.serializer(Unit.serializer()),
            formData {
                append("command", "APPEND")
                append("media_id", mediaId.id)
                media?.let { append("media", it.decodeToString()) }
                mediaData?.let { append("media_data", it) }
                append("segment_index", segmentIndex)
            }
        )
    }

    override suspend fun uploadFinalize(mediaId: MediaId): Response<MediaResult> {
        return client.submitFormWithBinaryData(
            "$API_MEDIA/upload.json",
            Response.serializer(MediaResult.serializer()),
            formData {
                append("command", "FINALIZE")
                append("media_id", mediaId.id)
            }
        )
    }

    override suspend fun uploadStatus(mediaId: MediaId): Response<MediaResult> {
        return client.get("$API_MEDIA/upload.json", Response.serializer(MediaResult.serializer())) {
            parameter("command", "STATUS")
            parameter("media_id", mediaId.id)
        }
    }

    override suspend fun createMetadata(mediaId: MediaId, altText: String): Response<Unit> {
        return client.post("$API_MEDIA/metadata/create.json", Response.serializer(Unit.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(MetadataRequest(mediaId, MetadataRequest.AltText(altText)))
        }
    }

    override suspend fun deleteSubtitles(
        mediaId: MediaId,
        mediaCategory: MediaCategory,
        languageCodes: List<String>
    ): Response<Unit> {
        return client.post("$API_MEDIA/subtitles/delete.json", Response.serializer(Unit.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(SubtitlesRequest.forDelete(mediaId, mediaCategory.name.lowercase(), languageCodes))
        }
    }

    override suspend fun createSubtitles(
        mediaId: MediaId,
        mediaCategory: MediaCategory,
        subtitles: List<Subtitle>
    ): Response<Unit> {
        return client.post("$API_MEDIA/subtitles/create.json", Response.serializer(Unit.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(SubtitlesRequest.forCreate(mediaId, mediaCategory.name.lowercase(), subtitles))
        }
    }
}
