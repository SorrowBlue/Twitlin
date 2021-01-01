package com.sorrowblue.twitlin.androidsample

import android.app.Application
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.initializeTwitlin

//private const val ACCESS_TOKEN = "938122027231150081-edSNSs0q0D9ahF9VW3zAUushpIbhrxz"
//private const val ACCESS_TOKEN_SECRET = "V5e6HQ7zfltRkghgR1B0jBeq4bHHmq0VDfNo5ZT32Otll"
private const val API_KEY = "ctNGOKkamPkXfFIcf4iQF37b7"
private const val API_KEY_SECRET = "BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T"

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeTwitlin(
            API_KEY,
            API_KEY_SECRET,
            bearerToken = BearerToken(
                "Bearer",
                "AAAAAAAAAAAAAAAAAAAAAP9CEgEAAAAAzu06w6WP7gs926FFpIpdvo92MbI%3DcYXjeFyh2YELwQm9iqN0Fy6ojxDG65v8ztOCgTLAmO6QcI16WJ"
            )
        )
    }
}
