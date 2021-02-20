/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.authentication.OAuth2Api
import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.authentication.impl.OAuth2ApiImpl
import com.sorrowblue.twitlin.authentication.impl.OAuthApiImpl
import com.sorrowblue.twitlin.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.directmessages.WelcomeMessagesApi
import com.sorrowblue.twitlin.directmessages.impl.DirectMessagesApiImpl
import com.sorrowblue.twitlin.directmessages.impl.WelcomeMessagesApiImpl
import com.sorrowblue.twitlin.geo.GeoApi
import com.sorrowblue.twitlin.geo.impl.GeoApiImpl
import com.sorrowblue.twitlin.media.MediaApi
import com.sorrowblue.twitlin.media.impl.MediaApiImpl
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.impl.TrendsApiImpl
import com.sorrowblue.twitlin.tweets.CollectionsApi
import com.sorrowblue.twitlin.tweets.FavoritesApi
import com.sorrowblue.twitlin.tweets.SearchApi
import com.sorrowblue.twitlin.tweets.StatusesApi
import com.sorrowblue.twitlin.tweets.impl.CollectionsApiImpl
import com.sorrowblue.twitlin.tweets.impl.FavoritesApiImp
import com.sorrowblue.twitlin.tweets.impl.SearchApiImpl
import com.sorrowblue.twitlin.tweets.impl.StatusesApiImp
import com.sorrowblue.twitlin.users.AccountApi
import com.sorrowblue.twitlin.users.BlocksApi
import com.sorrowblue.twitlin.users.FollowersApi
import com.sorrowblue.twitlin.users.FriendsApi
import com.sorrowblue.twitlin.users.FriendshipsApi
import com.sorrowblue.twitlin.users.ListsApi
import com.sorrowblue.twitlin.users.MutesApi
import com.sorrowblue.twitlin.users.SavedSearchesApi
import com.sorrowblue.twitlin.users.UsersApi
import com.sorrowblue.twitlin.users.impl.AccountApiImpl
import com.sorrowblue.twitlin.users.impl.BlocksApiImpl
import com.sorrowblue.twitlin.users.impl.FollowersApiImpl
import com.sorrowblue.twitlin.users.impl.FriendsApiImpl
import com.sorrowblue.twitlin.users.impl.FriendshipsApiImpl
import com.sorrowblue.twitlin.users.impl.ListsApiImpl
import com.sorrowblue.twitlin.users.impl.MutesApiImpl
import com.sorrowblue.twitlin.users.impl.SavedSearchesApiImpl
import com.sorrowblue.twitlin.users.impl.UsersApiImpl
import com.sorrowblue.twitlin.utilities.ApplicationApi
import com.sorrowblue.twitlin.utilities.HelpApi
import com.sorrowblue.twitlin.utilities.impl.ApplicationApiImpl
import com.sorrowblue.twitlin.utilities.impl.HelpApiImpl

/**
 * TODO
 */
public object TwitterAPI {

    // region authentication
    public val oauthApi: OAuthApi by lazy { OAuthApiImpl(Twitlin.userClient) }

    public val oauth2Api: OAuth2Api by lazy { OAuth2ApiImpl(Twitlin.appClient) }
    // endregion

    // region Tweets
    public val searchApi: SearchApi by lazy { SearchApiImpl(Twitlin.userClient) }
    // endregion

    // region users
    public val accountApi: AccountApi by lazy { AccountApiImpl(Twitlin.userClient) }

    public val blocksApi: BlocksApi by lazy { BlocksApiImpl(Twitlin.userClient) }

    public val mutesApi: MutesApi by lazy { MutesApiImpl(Twitlin.userClient) }

    public val followersApi: FollowersApi by lazy { FollowersApiImpl(Twitlin.userClient) }

    public val friendsApi: FriendsApi by lazy { FriendsApiImpl(Twitlin.userClient) }

    public val friendshipsApi: FriendshipsApi by lazy { FriendshipsApiImpl(Twitlin.userClient) }

    public val savedSearchesApi: SavedSearchesApi by lazy { SavedSearchesApiImpl(Twitlin.userClient) }

    public val usersApi: UsersApi by lazy { UsersApiImpl(Twitlin.userClient) }

    public val listsApi: ListsApi by lazy { ListsApiImpl(Twitlin.userClient) }
    // endregion

    // region utilities
    public val applicationApi: ApplicationApi by lazy { ApplicationApiImpl(Twitlin.userClient) }

    public val helpApi: HelpApi by lazy { HelpApiImpl(Twitlin.userClient) }
    // endregion

    // region geo
    public val geoApi: GeoApi by lazy { GeoApiImpl(Twitlin.userClient) }
    // endregion

    // region trends
    public val trendsApi: TrendsApi by lazy { TrendsApiImpl(Twitlin.userClient) }
    // endregion

    // region media
    public val mediaApi: MediaApi by lazy { MediaApiImpl(Twitlin.userClient) }
    // endregion

    // region direct messages
    public val directMessagesApi: DirectMessagesApi by lazy { DirectMessagesApiImpl(Twitlin.userClient) }

    public val welcomeMessagesApi: WelcomeMessagesApi by lazy { WelcomeMessagesApiImpl(Twitlin.userClient) }
    // endregion

    // region tweets
    public val statuses: StatusesApi by lazy { StatusesApiImp(Twitlin.userClient) }

    public val favorites: FavoritesApi by lazy { FavoritesApiImp(Twitlin.userClient) }

    public val collectionsApi: CollectionsApi by lazy { CollectionsApiImpl(Twitlin.userClient) }
    // endregion
}
