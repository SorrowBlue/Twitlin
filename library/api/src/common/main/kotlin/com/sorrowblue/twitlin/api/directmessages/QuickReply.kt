package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Quick reply
 *
 * @property type
 * @property options
 */
@AndroidParcelize
@Serializable
public data class QuickReply(
    val type: Type,
    val options: List<Option>? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * Type
     */
    @Serializable
    public enum class Type {

        /**
         * Options
         */
        @SerialName("options")
        OPTIONS
    }

    /**
     * Option
     *
     * @property label
     * @property description
     * @property metadata
     */
    @AndroidParcelize
    @Serializable
    public data class Option(
        val label: String,
        val description: String,
        val metadata: String
    ) : AndroidParcelable, JvmSerializable
}
