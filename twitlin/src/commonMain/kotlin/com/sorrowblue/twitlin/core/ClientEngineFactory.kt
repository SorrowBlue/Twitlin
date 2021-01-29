/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
