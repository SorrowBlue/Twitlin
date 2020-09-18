package com.sorrowblue.twitlin.androidsample

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import com.sorrowblue.twitlin.net.ErrorMessages
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.users.TwitterAPIV2
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
			TwitterAPI.oauth.requestToken("https://snsmate.sorrowblue.com")
				.onSuccess {
					url.postValue(TwitterAPI.oauth.authenticate(it))
				}
				.onError(errorCodes::postValue)
		}
	}

	@OptIn(TwitterAPIV2::class)
	val tweet =
		TwitterAPI.V2.tweetsApi.sampleStream(userFields = listOf(UserField.PROFILE_IMAGE_URL))
			.asLiveData(viewModelScope.coroutineContext)
}
