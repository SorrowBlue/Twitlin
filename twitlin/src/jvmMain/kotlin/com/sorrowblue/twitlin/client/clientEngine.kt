/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.client

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

public actual val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp