package com.sorrowblue.twitlin.core

import com.sorrowblue.twitlin.core.authentication.OAuth2Api
import com.sorrowblue.twitlin.core.authentication.OAuthApi
import com.sorrowblue.twitlin.core.authentication.impl.OAuth2ApiImpl
import com.sorrowblue.twitlin.core.authentication.impl.OAuthApiImpl
import com.sorrowblue.twitlin.core.client.Oauth1aClient
import com.sorrowblue.twitlin.core.client.Oauth2Client

public object CoreApiGetter {

    public fun oAuthApi(client: Oauth1aClient): OAuthApi = OAuthApiImpl(client)

    public fun oAuth2Api(client: Oauth2Client): OAuth2Api = OAuth2ApiImpl(client)
}
