/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.media.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.media.MediaApi
import com.sorrowblue.twitlin.media.MediaCategory
import com.sorrowblue.twitlin.media.MediaResult
import com.sorrowblue.twitlin.media.SubtitleInfo

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
        additionalOwners: List<String>?
    ): Response<MediaResult> = client.post(
        "$MEDIA/upload.json",
        "command" to "INIT",
        "total_bytes" to totalBytes,
        "media_type" to mediaType,
        "media_category" to mediaCategory?.name?.toLowerCase(),
        "additional_owners" to additionalOwners?.joinToString(",")
    )

    override suspend fun uploadAppend(
        mediaId: String,
        media: ByteArray?,
        mediaData: String?,
        segmentIndex: Int
    ): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadFinalize(mediaId: String): Response<MediaResult> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadStatus(mediaId: String): Response<MediaResult> {
        TODO("Not yet implemented")
    }

    override suspend fun createMetadata(mediaId: String, altText: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        languageCode: String
    ): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun createSubtitles(
        mediaId: String,
        mediaCategory: MediaCategory,
        subtitles: SubtitleInfo
    ): Response<Unit> {
        TODO("Not yet implemented")
    }
}
