package com.sorrowblue.twitlin.api.tweets.collections

import kotlinx.serialization.Serializable

/**
 * Collections
 *
 * @param O
 * @param R
 * @property objects
 * @property response
 * @constructor Create empty Collections
 */
@Serializable
public data class Collections<out O, out R : CollectionResponse>(
    val objects: O,
    val response: R
)
