package com.sorrowblue.twitlin.androidsample

import android.app.Application
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlinx.datetime.TimeZone

private const val API_KEY = "x4TxtotATeZhqbHsvfuqdLfPn"
private const val API_SECRET = "TZ4sHajdmySF3mKa1mFhYZev06fmKSpA0KWBPPVfFTVBpEMw1i"
private const val ACCESS_TOKEN = "938122027231150081-HQGZiJj6tBuH8b4LNqQxtHQzboTUFW1"
private const val ACCESS_TOKEN_SECRET = "a4CHH3zxnsVpstK2DaQH1ni5mwJWb2sT4ZGVAEYeUApOo"

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Twitlin.initialize(API_KEY, API_SECRET) {
            accessToken = AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET, "", "")
            bearerToken = BearerToken(
                "Bearer",
                "AAAAAAAAAAAAAAAAAAAAAP9CEgEAAAAA3Q7wz6%2FYWAOQ6gyHUncUUbstbmU%3DTnLENzSzesBt1ekF9CglgPpmyyFm7x0OMnfHN7KnBq5QQEkI2I"
            )
            timeZone = TimeZone.currentSystemDefault()
        }
    }
}
