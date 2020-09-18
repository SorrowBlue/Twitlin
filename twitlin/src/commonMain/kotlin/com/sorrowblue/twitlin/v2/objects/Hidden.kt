package com.sorrowblue.twitlin.v2.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hidden(@SerialName("hidden") val isHidden: Boolean)
