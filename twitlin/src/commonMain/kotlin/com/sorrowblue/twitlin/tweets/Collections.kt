/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import kotlinx.serialization.Serializable

@Serializable
public data class Collections<out O, out R : CollectionResponse>(
    val objects: O,
    val response: R
)
