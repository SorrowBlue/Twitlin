package com.sorrowblue.twitlin.androidsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sorrowblue.twitlin.Twitlin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		runBlocking {
			Twitlin.Api.statuses.homeTimeline(1)
				.onError {
					state.text = "ERROR"
					it.forEach {
						Log.e(javaClass.simpleName, "ERROR = $it")
					}
				}
				.onSuccess {
					state.text = "SUCCESS"
					it.forEach {
						Log.d(javaClass.simpleName, "SUCCESS = $it")
					}
				}
		}
	}
}
