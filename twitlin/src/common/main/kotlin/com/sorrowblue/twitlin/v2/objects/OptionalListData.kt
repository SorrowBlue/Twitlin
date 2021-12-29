package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.v2.client.Includes
import com.sorrowblue.twitlin.v2.client.Problem
import kotlin.collections.List
import kotlinx.serialization.Serializable

/**
 * Optional list data
 *
 * @param T
 * @property data
 * @property includes
 * @property errors
 * @constructor Create empty Optional list data
 */
@Serializable
public data class OptionalListData<T>(
    val data: List<T> = emptyList(),
    val includes: Includes = Includes(),
    val errors: List<Problem> = emptyList(),
)
