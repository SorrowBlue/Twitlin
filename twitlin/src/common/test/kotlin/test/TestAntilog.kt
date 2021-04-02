/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.Napier

internal class TestAntilog : Antilog() {

    override fun performLog(
        priority: Napier.Level,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) = println("${priority.name}: [$tag]: $message")
}
