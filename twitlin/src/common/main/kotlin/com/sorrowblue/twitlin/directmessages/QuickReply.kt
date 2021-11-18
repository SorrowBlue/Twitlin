package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
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
     * TODO
     *
     */
    @Serializable
    public enum class Type {

        /**
         * TODO
         */
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
    @AndroidParcelize
    @Serializable
    public data class Option(val label: String, val description: String, val metadata: String) :
        AndroidParcelable, JvmSerializable
}
