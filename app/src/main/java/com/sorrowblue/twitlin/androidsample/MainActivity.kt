package com.sorrowblue.twitlin.androidsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import com.sorrowblue.twitlin.client.Error
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        val oauth_token: String?
        val oauth_verifier: String?
        oauth_token = uri?.getQueryParameter("oauth_token")
        oauth_verifier = uri?.getQueryParameter("oauth_verifier")
        if (oauth_token != null && oauth_verifier != null) {
            viewModel.accessToken(oauth_token, oauth_verifier)
        }
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.title.text = "初期画面"
        binding.state.text = ""
        binding.button.text = "ログイン"
        binding.button.setOnClickListener { viewModel.requestAuthUrl() }
        viewModel.url.observe(this) {
            val uri = Uri.parse(it)
            val i = Intent(Intent.ACTION_VIEW, uri)
            startActivity(i)
            Log.d("APPAPP", "url = $it")
        }
        binding.recyclerView.adapter = viewModel.adapter
        lifecycleScope.launchWhenStarted {
            viewModel.tweet.collect {
                it.onSuccess { success ->
                    val tweet = success.data
//                    val user = success.includes?.users?.find { it.id == tweet.authorId }
//                    if (user != null) {
//                        viewModel.adapter.currentItem += tweet to user
//                    }
                    binding.state.text = viewModel.adapter.currentItem.size.toString()
                }
            }
        }
    }
}

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
