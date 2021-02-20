/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

class JvmSerializeTest {

    @Test
    fun testWriteTo() {
        val data = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
        ObjectOutputStream(FileOutputStream("testWriteTo")).use {
            it.writeObject(data)
        }
    }

    @Test
    fun testReadObject() {
        val data = ObjectInputStream(FileInputStream("testWriteTo")).use {
            it.readObject()
        }
        println(data)
    }
}
