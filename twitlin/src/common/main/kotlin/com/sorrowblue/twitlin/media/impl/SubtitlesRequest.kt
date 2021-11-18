package com.sorrowblue.twitlin.media.impl

import com.sorrowblue.twitlin.media.Subtitle
import com.sorrowblue.twitlin.objects.MediaId
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property media_id
 * @property media_category
 * @property subtitle_info
 */
@Serializable
internal class SubtitlesRequest(
    val media_id: MediaId,
    val media_category: String,
    val subtitle_info: Info
) {

    /**
     * TODO
     *
     * @property subtitles
     */
    @Serializable
    class Info(val subtitles: List<Subtitle>)

    companion object {
        fun forDelete(media_id: MediaId, media_category: String, language_codes: List<String>) =
            SubtitlesRequest(
                media_id,
                media_category,
                Info(language_codes.map { Subtitle(MediaId(""), it, "") })
            )

        fun forCreate(media_id: MediaId, media_category: String, subtitles: List<Subtitle>) =
            SubtitlesRequest(media_id, media_category, Info(subtitles))
    }
}
