package com.sorrowblue.twitlin.accounts_users.followers

import com.sorrowblue.twitlin.accounts_users.UserIds
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.PagingTwitterUser

interface FollowersApi {

    suspend fun ids(
        userId: Long,
        cursor: Long? = null,
        stringifyIds: Boolean? = null,
        count: Int? = null
    ): Response<UserIds>

    suspend fun ids(
        screenName: String,
        cursor: Long? = null,
        stringifyIds: Boolean? = null,
        count: Int? = null
    ): Response<UserIds>

    suspend fun list(
        userId: Long,
        cursor: Long? = null,
        count: Int? = null,
        skipStatus: Boolean? = null,
        includeUserEntities: Boolean? = null
    ): Response<PagingTwitterUser>

    suspend fun list(
        screenName: String,
        cursor: Long? = null,
        count: Int? = null,
        skipStatus: Boolean? = null,
        includeUserEntities: Boolean? = null
    ): Response<PagingTwitterUser>
}
