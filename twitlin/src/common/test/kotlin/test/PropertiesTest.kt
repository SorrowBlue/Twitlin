/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PropertiesTest {

    private lateinit var properties: Properties

    @BeforeTest
    fun setUp() {
        properties = Properties("/test/test.properties")
    }

    @Test
    fun testGetProperty() {
        val value = properties.getProperty("PROPERTY_TEST")
        assertEquals("0", value)
    }

    @Test
    fun testGetProperty_default() {
        val value = properties.getProperty("PROPERTY_TEST", "1")
        assertEquals("0", value)
    }

    @Test
    fun testGetProperty_empty() {
        val propertyTest = properties.getProperty("PROPERTY_TEST_EMPTY")
        assertEquals("", propertyTest)
    }

    @Test
    fun testGetProperty_empty_default() {
        val value = properties.getProperty("PROPERTY_TEST_EMPTY", "2")
        assertEquals("", value)
    }

    @Test
    fun testGetProperty_undefined() {
        val value = properties.getProperty("PROPERTY_TEST_UNDEFINED")
        assertEquals(null, value)
    }

    @Test
    fun testGetProperty_undefined_default() {
        val value = properties.getProperty("PROPERTY_TEST_UNDEFINED", "3")
        assertEquals("3", value)
    }
}
