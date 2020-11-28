package com.sorrowblue.twitlin.net

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
