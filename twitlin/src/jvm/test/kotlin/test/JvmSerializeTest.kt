/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import org.junit.Test
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class JvmSerializeTest {

    @Test
    fun testWriteAndReadObject() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            .toJavaLocalDateTime()
        ObjectOutputStream(FileOutputStream("testWriteTo")).use {
            it.writeObject(data)
        }
        println(data)
    }
}
