/*
 * (c) 2021 SorrowBlue.
 */

package test

import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class TestUtilsTest {

    @Test
    fun testLoadFile() {
        val text = TestUtils.loadFile("/test/load_file.txt")
        assertEquals(
            """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            In id ipsum tempor purus mattis porttitor.
            Nam commodo ex in dui accumsan, nec porttitor tortor scelerisque.
            Vivamus luctus turpis in lacinia convallis.
            
        """.trimIndent(), text
        )
    }

    @Test
    fun testLoadFile_blank() {
        assertFails {
            TestUtils.loadFile("/test/undefined_load_file.txt")
        }
    }

    @Test
    fun testRunBlocking() {
        TestUtils.runBlocking {
            var count = 0
            var beforeTime = Clock.System.now().toEpochMilliseconds()
            for (i in 1..3) {
                delay(100)
                count++
                val newTime = Clock.System.now().toEpochMilliseconds()
                assertTrue(
                    beforeTime + 100 < newTime
                )
                beforeTime = newTime
            }
            assertEquals(3, count)
        }
    }
}
