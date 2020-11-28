package com.sorrowblue.twitlin.net

import io.ktor.client.engine.*


internal expect val clientEngine: HttpClientEngineFactory<HttpClientEngineConfig>
