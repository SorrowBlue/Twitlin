package com.sorrowblue.twitlin.net

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = Android
