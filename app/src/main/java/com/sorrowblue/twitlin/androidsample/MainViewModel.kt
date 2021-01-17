/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.androidsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.client.Error
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val adapter = MainAdapter()

    val errorCodes: MutableLiveData<List<Error>> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()

    fun requestAuthUrl() {
        viewModelScope.launch {
            TwitterAPI.oauthApi.requestToken("https://twitlinsample.sorrowblue.com")
                .onSuccess {
                    url.postValue(TwitterAPI.oauthApi.authenticate(it.oauthToken))
                }
                .onError {
                    errorCodes.postValue(it)
                }
        }
    }

    fun accessToken(oauthToken: String, oauthVerifier: String) {
        viewModelScope.launch {
            TwitterAPI.oauthApi.accessToken(oauthToken, oauthVerifier).dataOrNull()?.let {
                Twitlin.accessToken = it
            }
        }
    }

    val tweet = TwitterV2API.tweetsAppApi.sampleStream(
        expansions = listOf(Expansion.AUTHOR_ID),
        userFields = listOf(UserField.PROFILE_IMAGE_URL),
        tweetFields = listOf(TweetField.TEXT, TweetField.AUTHOR_ID)
    )
}
