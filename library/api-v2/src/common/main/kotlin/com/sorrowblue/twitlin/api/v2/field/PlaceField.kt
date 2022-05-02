package com.sorrowblue.twitlin.api.v2.field

public enum class PlaceField(override val value: String) : OptionalField {
    CONTAINED_WITHIN("contained_within"),
    COOUNTRY("country"),
    COUNTRY_CODE("country_code"),
    FULL_NAME("full_name"),
    GEO("geo"),
    ID("id"),
    NAME("name"),
    PLACE_TYPE("place_type");

    public companion object {
        public fun all(): List<PlaceField> = listOf(
            CONTAINED_WITHIN,
            COOUNTRY,
            COUNTRY_CODE,
            FULL_NAME,
            GEO,
            ID,
            NAME,
            PLACE_TYPE
        )
    }
}
