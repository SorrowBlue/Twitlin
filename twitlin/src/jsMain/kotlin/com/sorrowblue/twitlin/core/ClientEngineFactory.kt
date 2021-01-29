/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

internal actual val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = Js
