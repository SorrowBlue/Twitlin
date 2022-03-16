package com.sorrowblue.twitlin.api.v2

public object Endpoints {

    private const val BASE: String = "https://api.twitter.com/2"
    public const val USERS: String = "$BASE/users"
    public const val TWEETS: String = "$BASE/tweets"
    public const val TWEETS_SEARCH: String = "$TWEETS/search"
    public const val TWEETS_COUNTS: String = "$TWEETS/counts"
    public const val OAUTH2: String = "$BASE/oauth2"
    public const val SPACES: String = "$BASE/spaces"
    public const val LISTS: String = "$BASE/lists"
}
