/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.field

public enum class MediaField(override val value: String) : OptionalField {
    DURATION_MS("duration_ms"),
    HEIGHT("height"),
    MEDIA_KEY("media_key"),
    PREVIEW_IMAGE_URL("preview_image_url"),
    TYPE("type"),
    URL("url"),
    WIDTH("width"),
    PUBLIC_METRICS("public_metrics"),
    NON_PUBLIC_METRICS("non_public_metrics"),
    ORGANIC_METRICS("organic_metrics"),
    PROMOTED_METRICS("promoted_metrics");

    public companion object {
        public fun all(): List<MediaField> = listOf(
            DURATION_MS, HEIGHT,
            MEDIA_KEY,
            PREVIEW_IMAGE_URL,
            TYPE,
            URL,
            WIDTH,
            PUBLIC_METRICS,
            NON_PUBLIC_METRICS,
            ORGANIC_METRICS,
            PROMOTED_METRICS
        )
    }
}
