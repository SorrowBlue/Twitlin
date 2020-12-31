/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Hidden(@SerialName("hidden") val isHidden: Boolean) : JvmSerializable
