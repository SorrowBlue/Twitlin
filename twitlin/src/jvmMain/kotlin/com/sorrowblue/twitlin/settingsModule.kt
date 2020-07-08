package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.bind

internal actual fun Module.settingsModule() {
	single { JavaSettings() } bind Settings::class
}
