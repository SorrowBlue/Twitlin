/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.Serializable

@Serializable
public data class DirectMessageApp(val id: String, val name: String, val url: String)
