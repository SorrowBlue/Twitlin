package com.sorrowblue.twitlin.api.v2.users

import com.sorrowblue.twitlin.api.v2.field.OptionalField

public enum class Exclude(override val value: String) : OptionalField {
    RETWEETS("retweets"),
    REPLIES("replies")
}
