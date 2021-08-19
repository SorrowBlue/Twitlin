package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import com.sorrowblue.twitlin.v2.client.Includes
import kotlinx.serialization.Serializable

@Serializable
public data class OptionalData<T>(
    val data: T,
    val includes: Includes? = null,
    val errors: List<Error>? = null,
) : JvmSerializable

