package com.sorrowblue.twitlin.api.media.impl

import com.sorrowblue.twitlin.api.media.Subtitle
import com.sorrowblue.twitlin.core.objects.MediaId
import kotlinx.serialization.Serializable

@Serializable
internal class SubtitlesRequest(
    val media_id: MediaId,
    val media_category: String,
    val subtitle_info: Info
) {

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
