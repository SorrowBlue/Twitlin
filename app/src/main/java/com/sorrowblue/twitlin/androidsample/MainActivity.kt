/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.androidsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.sorrowblue.twitlin.androidsample.databinding.ActivityMainBinding
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

internal class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

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
            viewModel.tweet.collect {
                delay(1000)
                it.onSuccess { success ->
                    val tweet = success.data.data
                    val user = success.data.includes?.users?.find { it.id == tweet.authorId }
                    if (user != null) {
                        viewModel.adapter.currentItem =
                            listOf(tweet to user) + viewModel.adapter.currentItem
                    }
                    binding.state.text = viewModel.adapter.currentItem.size.toString()
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        }
        viewModel.adapter.currentItem += Tweet(
            authorId = "4727639925",
            id = "1350774705633046529",
            text = "Hambre de ti 😈💫 https://t.co/oRC3WGVO6c"
        ) to User(
            username = "lokdeseass",
            id = "4727639925",
            profileImageUrl = "https://pbs.twimg.com/profile_images/1302597533592682496/PejMYjSK_normal.jpg",
            name = "Martha"
        )
    }
}
