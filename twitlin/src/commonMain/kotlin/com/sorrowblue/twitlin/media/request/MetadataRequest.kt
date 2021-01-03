/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media.request

import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property media_id
 * @property alt_text
 */
@Serializable
internal class MetadataRequest(val media_id: String, val alt_text: AltText) {

    /**
     * TODO
     *
     * @property text
     */
    @Serializable
    class AltText(val text: String)
}
