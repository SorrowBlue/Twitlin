/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media.request

import com.sorrowblue.twitlin.media.Subtitle
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
    val media_id: String,
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
        fun forDelete(media_id: String, media_category: String, language_codes: List<String>) =
            SubtitlesRequest(
                media_id,
                media_category,
                Info(language_codes.map { Subtitle("", it, "") })
            )

        fun forCreate(media_id: String, media_category: String, subtitles: List<Subtitle>) =
            SubtitlesRequest(media_id, media_category, Info(subtitles))
    }
}
