/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.field

public enum class UserField(override val value: String) : OptionalField {
    CREATED_AT("created_at"),
    DESCRIPTION("description"),
    ENTITIES("entities"),
    ID("id"),
    LOCATION("location"),
    NAME("name"),
    PINNED_TWEET_ID("pinned_tweet_id"),
    PROFILE_IMAGE_URL("profile_image_url"),
    PROTECTED("protected"),
    PUBLIC_METRICS("public_metrics"),
    URL("url"),
    USERNAME("username"),
    VERIFIED("verified"),
    WITHHELD("withheld"),
    ;

    public companion object {
        public fun all(): List<UserField> = listOf(
            CREATED_AT,
            DESCRIPTION,
            ENTITIES,
            ID,
            LOCATION,
            NAME,
            PINNED_TWEET_ID,
            PROFILE_IMAGE_URL,
            PROTECTED,
            PUBLIC_METRICS,
            URL,
            USERNAME,
            VERIFIED,
            WITHHELD
        )
    }
}
