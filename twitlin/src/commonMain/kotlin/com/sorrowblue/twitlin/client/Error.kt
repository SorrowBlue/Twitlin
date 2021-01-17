/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import kotlinx.serialization.Serializable

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
