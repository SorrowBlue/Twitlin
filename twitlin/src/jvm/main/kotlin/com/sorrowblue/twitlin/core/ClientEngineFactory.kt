/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
