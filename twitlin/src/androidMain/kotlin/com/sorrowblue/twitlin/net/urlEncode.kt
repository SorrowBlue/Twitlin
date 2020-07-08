package com.sorrowblue.twitlin.net

import java.net.URLEncoder

internal actual fun String.urlEncode(): String = URLEncoder.encode(this, "UTF-8")
	.replace("+", "%2B")
	.replace("!", "%21")
	.replace(",", "%2C")
	.replace("&", "%26")
