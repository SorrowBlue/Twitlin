package com.sorrowblue.twitlin.net

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

internal actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = Js
