/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.users.RelationshipDetail
import kotlinx.serialization.Serializable

@Serializable
internal class RelationshipDetailResult(val relationship: RelationshipDetail) {
    companion object {
        fun onSuccess(res: RelationshipDetailResult) =
            Response.Success(res.relationship)
    }
}
