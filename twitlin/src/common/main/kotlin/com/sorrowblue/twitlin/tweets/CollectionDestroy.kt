/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@Serializable
public data class CollectionDestroy(val destroyed: Boolean) : JvmSerializable
