/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property code
 * @property name
 * @property localName
 * @property status
 * @property debug
 */
@Serializable
public data class Language(
    val code: String,
    val name: String,
    @SerialName("local_name") val localName: String,
    val status: String,
    val debug: Boolean
) : JvmSerializable

/**
 * TODO
 *
 * @constructor
 * TODO
 *
 * @param value
 */
@Serializable
public enum class LanguageCode(public val value: String) {
    @SerialName("en")
    ENGLISH("en"),

    @SerialName("eu")
    EUROPEAN_UNION("eu"),

    @SerialName("et")
    ESTONIAN("et"),

    @SerialName("ar")
    ARABIC("ar"),

    @SerialName("bn")
    BENGALI("bn"),

    @SerialName("cs")
    CZECH("cs"),

    @SerialName("da")
    DANISH("da"),

    @SerialName("de")
    GERMAN("de"),

    @SerialName("el")
    GREEK("el"),

    @SerialName("es")
    SPANISH("es"),

    @SerialName("fa")
    PERSIAN("fa"),

    @SerialName("fi")
    FINNISH("fi"),

    @SerialName("fil")
    FILIPINO("fil"),

    @SerialName("fr")
    FRENCH("fr"),

    @SerialName("he")
    HEBREW("he"),

    @SerialName("hi")
    HINDI("hi"),

    @SerialName("hu")
    HUNGARIAN("hu"),

    @SerialName("id")
    INDONESIAN("id"),

    @SerialName("it")
    ITALIAN("it"),

    @SerialName("ja")
    JAPANESE("ja"),

    @SerialName("ko")
    KOREAN("ko"),

    @SerialName("msa")
    MALAY("msa"),

    @SerialName("nl")
    DUTCH("nl"),

    @SerialName("no")
    NORWEGIAN("no"),

    @SerialName("pl")
    POLISH("pl"),

    @SerialName("pt")
    PORTUGUESE("pt"),

    @SerialName("ro")
    ROMANIAN("ro"),

    @SerialName("ru")
    RUSSIAN("ru"),

    @SerialName("sv")
    SWEDISH("sv"),

    @SerialName("th")
    THAI("th"),

    @SerialName("tr")
    TURKISH("tr"),

    @SerialName("uk")
    UKRAINIAN("uk"),

    @SerialName("ur")
    URDU("ur"),

    @SerialName("vi")
    VIETNAMESE("vi"),

    @SerialName("zh-cn")
    CHINESE_SIMPLIFIED("zh-cn"),

    @SerialName("zh-tw")
    CHINESE_TRADITIONAL("zh-tw"),

    @SerialName("und")
    UNDETERMINED("und"),

    @SerialName("in")
    IN("in")
}
