package com.sorrowblue.twitlin.net

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

actual val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig> = Android
