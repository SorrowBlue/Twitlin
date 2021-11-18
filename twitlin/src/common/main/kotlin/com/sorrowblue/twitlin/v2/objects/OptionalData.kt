package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.v2.client.Error
import com.sorrowblue.twitlin.v2.client.Includes
import kotlin.collections.List
import kotlinx.serialization.Serializable

/**
 * Optional data
 *
 * @param T
 * @property data
 * @property includes
 * @property errors
 * @constructor Create empty Optional data
 */
@Serializable
public data class OptionalData<T>(
    val data: T,
    val includes: Includes = Includes(),
    val errors: List<Error>? = null,
)

