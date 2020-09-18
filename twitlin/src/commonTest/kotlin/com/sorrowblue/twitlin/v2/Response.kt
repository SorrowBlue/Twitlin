package com.sorrowblue.twitlin.v2

import com.github.aakira.napier.Napier

internal fun <T: Any> Response<T>.testResult(): T? {
	onSuccess {
		Napier.d("onSuccess() = $it", tag = "Test")
	}.onFailure {
		Napier.e("onFailure() = $it", tag = "Test")
	}
	return dataOrNull()
}
