package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.field.OptionalField

public enum class Exclude(override val value: String) : OptionalField {
    RETWEETS("retweets"),
    REPLIES("replies")
}
