package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.Napier

class TestAntilog : Antilog() {
	override fun performLog(
		priority: Napier.Level,
		tag: String?,
		throwable: Throwable?,
		message: String?
	) {
		println("${priority.name}: [$tag]: $message")

	}
}
