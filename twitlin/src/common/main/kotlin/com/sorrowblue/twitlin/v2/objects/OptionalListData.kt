package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import com.sorrowblue.twitlin.v2.client.Includes
import kotlinx.serialization.Serializable

@Serializable
public data class OptionalListData<T>(
    val data: List<T> = emptyList(),
    val includes: Includes? = null,
    val errors: List<Error>? = null,
) : JvmSerializable
