/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

/**
 * In addition to descriptive error text, error messages contain machine-parseable codes. While the
 * text for an error message may change, the codes will stay the same.
 *
 * The following table describes the codes which may appear when working with the standard API
 * (note that the Ads API and some other resource families may present additional error codes).
 * If an error response is not listed in the table, fall back to examining the HTTP status codes
 * above in order to determine the best way to address the issue.
 *
 * @see <a href="https://developer.twitter.com/ja/docs/basics/response-codes">Error Codes</a>
 */
public object ErrorCodes {

    /**
     * If [code] is defined by the Twitter API, `true` returns.
     *
     * @param code For example, [ErrorMessages.Error.code].
     * @return Whether it is defined by the Twitter API
     */
    public fun isOfficial(code: Int): Boolean = code in setOf(
        3, 13, 17, 32, 34, 36, 38, 44, 50, 63, 64, 68, 87, 88, 89, 92, 93, 99,
        120, 130, 131, 135, 139, 144, 150, 151, 160, 161, 179, 185, 186, 187, 195,
        205, 214, 215, 220, 226, 231, 251, 261, 271, 272,
        323, 324, 325, 326, 327, 349, 354, 355, 385, 386,
        407, 415, 416, 417
    )

    public const val CLIENT_ERROR: Int = 1000
}
