package com.sorrowblue.twitlin.api.oauth

/**
 * Defines the level of access that the application requires for user accounts.
 */
public enum class XAuthAccessType {

    /**
     * Request read-only access with [OAuthApi.requestToken].
     */
    READ,

    /**
     * Request read / write access with [OAuthApi.requestToken].
     */
    WRITE
}
