package com.sorrowblue.twitlin.media.impl

import com.sorrowblue.twitlin.objects.MediaId
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property media_id
 * @property alt_text
 */
@Serializable
internal class MetadataRequest(val media_id: MediaId, val alt_text: AltText) {

    /**
     * TODO
     *
     * @property text
     */
    @Serializable
    class AltText(val text: String)
}
