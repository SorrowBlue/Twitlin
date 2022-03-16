package com.sorrowblue.twitlin.core.objects

public interface UUID {
    public val id: String
}

public fun List<UUID>.toParameter(): String = joinToString(",", transform = UUID::id)
