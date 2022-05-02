package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property sizes
 */
@AndroidParcelize
@Serializable
public data class ProfileBanner(val sizes: Sizes) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property ipad
     * @property ipadRetina
     * @property web
     * @property webRetina
     * @property mobile
     * @property mobileRetina
     * @property _300x100
     * @property _600x200
     * @property _1500x500
     */
    @AndroidParcelize
    @Serializable
    public data class Sizes(
        val ipad: Device,
        @SerialName("ipad_retina") val ipadRetina: Device,
        val web: Device,
        @SerialName("web_retina") val webRetina: Device,
        val mobile: Device,
        @SerialName("mobile_retina") val mobileRetina: Device,
        @SerialName("300x100")
        val _300x100: Device,
        @SerialName("600x200")
        val _600x200: Device,
        @SerialName("1500x500")
        val _1500x500: Device
    ) : AndroidParcelable, JvmSerializable {

        /**
         * TODO
         *
         * @property h
         * @property w
         * @property url
         */
        @AndroidParcelize
        @Serializable
        public data class Device(val h: Int, val w: Int, val url: String) : AndroidParcelable, JvmSerializable
    }
}
