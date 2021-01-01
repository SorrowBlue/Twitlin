package com.sorrowblue.twitlin.androidsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import com.sorrowblue.twitlin.client.ErrorMessages
import com.sorrowblue.twitlin.v2.tweets.UserField
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.title.text = "初期画面"
        binding.state.text = ""
        binding.button.text = "ログイン"
        binding.button.setOnClickListener { viewModel.requestAuthUrl() }
        viewModel.url.observe(this) {
            Log.d("APPAPP", "url = $it")
        }
        binding.recyclerView.adapter = viewModel.adapter
        viewModel.tweet.observe(this) {
            it.onSuccess {
                viewModel.adapter.currentItem += it
            }
        }
    }
}

class MainViewModel : ViewModel() {

    val adapter = MainAdapter()

    val errorCodes: MutableLiveData<List<ErrorMessages.Error>> = MutableLiveData()
    val url: MutableLiveData<String> = MutableLiveData()

    fun requestAuthUrl() {
        viewModelScope.launch {
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com")
                .onSuccess {
                    url.postValue(TwitterAPI.oauthApi.authenticate(it.oauthToken))
                }
                .onError {
                    errorCodes.postValue(it.errors)
                    it.errors
                }
        }
    }

    val tweet =
        TwitterV2API.tweetsApi.sampleStream(userFields = listOf(UserField.PROFILE_IMAGE_URL))
            .asLiveData(viewModelScope.coroutineContext)
}
