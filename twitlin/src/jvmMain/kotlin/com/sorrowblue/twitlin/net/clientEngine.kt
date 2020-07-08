package com.sorrowblue.twitlin.net

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
