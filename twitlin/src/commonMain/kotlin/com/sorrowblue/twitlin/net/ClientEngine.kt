package com.sorrowblue.twitlin.net

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig>
