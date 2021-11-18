package com.sorrowblue.twitlin.androidsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(javaClass.simpleName, "Log test")
        Log.e(javaClass.simpleName, "Log test")
        Log.w(javaClass.simpleName, "Log test")
        Log.i(javaClass.simpleName, "Log test")
        Log.v(javaClass.simpleName, "Log test")
        Log.wtf(javaClass.simpleName, "Log test")
        super.onCreate(savedInstanceState)
        val uri = intent.data
        val oauthToken: String? = uri?.getQueryParameter("oauth_token")
        val oauthVerifier: String? = uri?.getQueryParameter("oauth_verifier")
        if (oauthToken != null && oauthVerifier != null) {
            viewModel.accessToken(oauthToken, oauthVerifier)
        }
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.title.text = "初期画面"
        binding.state.text = ""
        binding.button.text = "ログイン"
        binding.button.setOnClickListener { viewModel.requestAuthUrl() }
        viewModel.url.observe(this) {
            Log.d(this::class.java.simpleName, "url = $it")
            Intent(Intent.ACTION_VIEW, Uri.parse(it)).also(this::startActivity)
        }
        binding.recyclerView.adapter = viewModel.adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        )
        lifecycleScope.launchWhenStarted {
            viewModel.tweet.collect { optionalData ->
                delay(300)
                val tweet = optionalData.data
                val user = optionalData.includes.users.find { it.id == tweet.authorId }
                if (user != null) {
                    viewModel.adapter.submitList(listOf(tweet to user) + viewModel.adapter.currentList)
                }
                binding.state.text = viewModel.adapter.itemCount.toString()
                binding.recyclerView.smoothScrollToPosition(0)
            }
        }
    }
}
