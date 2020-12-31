/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.runTest

class OAuthApiTest {

    fun requestTokenTest() = runTest {
        TwitterAPI.oauth.requestToken("")
    }
}
