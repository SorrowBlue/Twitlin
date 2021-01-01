/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property type
 * @property options
 */
@Serializable
public data class QuickReply(
    val type: Type,
    val options: List<Option>? = null
) {

    /**
     * TODO
     *
     */
    @Serializable
    public enum class Type {
        @SerialName("options")
        OPTIONS
    }

    /**
     * TODO
     *
     * @property label
     * @property description
     * @property metadata
     */
    @Serializable
    public data class Option(
        val label: String,
        val description: String,
        val metadata: String
    )
}
