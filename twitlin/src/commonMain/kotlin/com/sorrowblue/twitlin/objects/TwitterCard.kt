/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@Serializable
public data class TwitterCard(
    val title: String,
    val url: String,
    val description: String,
    val image: String,
    val type: Type,
    val site: String
) : JvmSerializable {
    public enum class Type {
        SUMMARY,
        SUMMARY_LARGE_IMAGE,
        APP,
        PLAYER;

        public companion object {

            public fun parse(value: String): Type =
                runCatching { valueOf(value.toUpperCase()) }.getOrElse { SUMMARY }
        }
    }
}
