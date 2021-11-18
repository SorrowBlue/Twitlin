package com.sorrowblue.twitlin.v2.field

public interface OptionalField {
    public val value: String
}

public fun List<OptionalField>.toParameter(): String = joinToString(",") { it.value }
