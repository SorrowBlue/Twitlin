package com.sorrowblue.twitlin.net

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ios.Ios

internal actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = Ios
