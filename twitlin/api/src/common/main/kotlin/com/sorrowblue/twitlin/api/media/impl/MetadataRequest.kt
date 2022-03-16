package com.sorrowblue.twitlin.api.media.impl

import com.sorrowblue.twitlin.core.objects.MediaId
import kotlinx.serialization.Serializable

@Serializable
internal class MetadataRequest(val media_id: MediaId, val alt_text: AltText) {

    @Serializable
    class AltText(val text: String)
}
