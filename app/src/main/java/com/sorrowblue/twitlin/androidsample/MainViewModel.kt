package com.sorrowblue.twitlin.androidsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.client.Oauth1aClient
import com.sorrowblue.twitlin.api.client.Oauth2Client
import com.sorrowblue.twitlin.api.oauth.OAuthApi
import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.tweets.Expansion
import com.sorrowblue.twitlin.api.v2.tweets.TweetsApi
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

interface TwitterRepository {
    fun oauthApi(): OAuthApi
    fun tweetsAppApi(): TweetsApi
}

class TwitterRepositoryImpl(private val oauth1aClient: Oauth1aClient, private val oauth2Client: Oauth2Client) :
    TwitterRepository {
    override fun oauthApi(): OAuthApi {
        return TwitlinApi.getApi<OAuthApi>(oauth1aClient)
    }

    override fun tweetsAppApi(): TweetsApi {
        return TwitlinApiV2.getApi<TweetsApi>(oauth2Client)
    }

}

class MainViewModel(val repository: TwitterRepository) : ViewModel() {

    val adapter = MainAdapter()

    val errorCodes: MutableLiveData<List<com.sorrowblue.twitlin.api.client.Error>> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()

    fun requestAuthUrl() {
        viewModelScope.launch {
            repository.oauthApi().requestToken("https://twitlinsample.sorrowblue.com")
                .onSuccess {
                    url.postValue(repository.oauthApi().authenticate(it.oauthToken))
                }
                .onError {
                    errorCodes.postValue(it)
                }
        }
    }

    fun accessToken(oauthToken: String, oauthVerifier: String) {
        viewModelScope.launch {
            repository.oauthApi().accessToken(oauthToken, oauthVerifier).dataOrNull()?.let {
                accessToken = it
            }
        }
    }

    val tweet = repository.tweetsAppApi().sampleStream(
        expansions = listOf(Expansion.AUTHOR_ID),
        userFields = listOf(UserField.PROFILE_IMAGE_URL),
        tweetFields = listOf(TweetField.TEXT, TweetField.AUTHOR_ID)
    ).mapNotNull { it.dataOrNull() }
}
