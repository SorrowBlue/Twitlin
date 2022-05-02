package com.sorrowblue.twitlin.core.objects

import com.sorrowblue.twitlin.core.annotation.JvmSerializable

public interface UUID : JvmSerializable {
    public val id: String
}

public fun List<UUID>.toParameter(): String = joinToString(",", transform = UUID::id)
