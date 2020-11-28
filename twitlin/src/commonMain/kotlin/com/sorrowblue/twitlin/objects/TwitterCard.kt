package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TwitterCard(
    val title: String,
    val url: String,
    val description: String,
    val image: String,
    val type: Type,
    val site: String
) : Parcelable {
    enum class Type {
        SUMMARY,
        SUMMARY_LARGE_IMAGE,
        APP,
        PLAYER;

        companion object {

            fun parse(value: String) =
                runCatching { valueOf(value.toUpperCase()) }.getOrElse { SUMMARY }
        }
    }
}
