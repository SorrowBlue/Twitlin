package com.sorrowblue.twitlin.androidsample

import android.app.Application
import com.sorrowblue.twitlin.api.client.ConsumerKeys
import com.sorrowblue.twitlin.api.client.Oauth1aClient
import com.sorrowblue.twitlin.api.client.Oauth2Client
import com.sorrowblue.twitlin.api.oauth.AccessToken
import com.sorrowblue.twitlin.api.oauth2.BearerToken
import com.sorrowblue.twitlin.core.objects.UserId
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

var accessToken =
    AccessToken(BuildConfig.ACCESS_TOKEN, BuildConfig.ACCESS_TOKEN_SECRET, UserId(""), "")
var bearerToken = BearerToken("bearer", BuildConfig.BEARER_TOKEN)

val appModule = module {

    // single instance of HelloRepository
    factory<TwitterRepository> { TwitterRepositoryImpl(get(), get()) }

    factory { ConsumerKeys(BuildConfig.APP_KEY, BuildConfig.APP_KEY_SECRET) }
    factory { accessToken }
    factory { bearerToken }

    factory { Oauth1aClient(get(), get()) }
    factory { Oauth2Client(get(), get()) }

    // MyViewModel ViewModel
    viewModel { MainViewModel(get()) }

}

internal class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
