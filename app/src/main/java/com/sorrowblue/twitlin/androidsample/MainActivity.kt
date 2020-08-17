package com.sorrowblue.twitlin.androidsample

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import com.sorrowblue.twitlin.net.ErrorMessages
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
			if (it != null) {
				binding.webview.loadUrl(it)
			}
		}
	}
}

class MainViewModel : ViewModel() {

	val errorCodes: MutableLiveData<List<ErrorMessages.Error>> = MutableLiveData()
	val url: MutableLiveData<String> = MutableLiveData()

	fun requestAuthUrl() {
		viewModelScope.launch {
			Twitlin.Api.oauth.requestToken("https://snsmate.sorrowblue.com")
				.onSuccess {
					url.postValue(Twitlin.Api.oauth.authenticate(it))
				}
				.onError(errorCodes::postValue)
		}
	}

}
