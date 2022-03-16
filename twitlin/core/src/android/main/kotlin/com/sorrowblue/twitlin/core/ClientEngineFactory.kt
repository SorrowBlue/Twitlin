package com.sorrowblue.twitlin.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

public actual val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = Android
