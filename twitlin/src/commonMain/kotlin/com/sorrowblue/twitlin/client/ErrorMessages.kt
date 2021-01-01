/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property errors TODO
 */
@Serializable
public data class ErrorMessages(
    val errors: List<Error>,
) {

    /**
     * TODO
     *
     * @property message TODO
     * @property code TODO
     */
    @Serializable
    public data class Error(
        val message: String,
        val code: Int,
    )

}
