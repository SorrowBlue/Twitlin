package com.sorrowblue.twitlin.v2.oauth2

public enum class OAuth2Scope(public val value: String) {
    TWEET_READ("tweet.read"),
    USERS_READ("users.read"),
    ACCOUNT_FOLLOWS_READ("account.follows.read"),
    ACCOUNT_FOLLOWS_WRITE("account.follows.write"),
    OFFLINE_ACCESS("offline.access"),

    @Deprecated("This value is not support yet.", level = DeprecationLevel.HIDDEN)
    SPACES_READ("spaces.read");

    public companion object {
        public fun all(): List<OAuth2Scope> = listOf(TWEET_READ,USERS_READ, ACCOUNT_FOLLOWS_READ, ACCOUNT_FOLLOWS_WRITE, OFFLINE_ACCESS)
    }
}
