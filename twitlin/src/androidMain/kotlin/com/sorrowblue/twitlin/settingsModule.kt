package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.settings.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.bind

internal actual fun Module.settingsModule() {
	single { AndroidSettings(androidContext()) } bind Settings::class
}
