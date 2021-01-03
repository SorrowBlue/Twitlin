/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.Serializable

@Serializable
public data class DirectMessage(val event: DirectMessageEvent)
