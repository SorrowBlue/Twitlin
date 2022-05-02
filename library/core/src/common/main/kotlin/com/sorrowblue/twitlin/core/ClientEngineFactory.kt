package com.sorrowblue.twitlin.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

public expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
