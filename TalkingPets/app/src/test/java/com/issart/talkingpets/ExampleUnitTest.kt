package com.issart.talkingpets

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sin

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getWrightScale() {
        val degrees = 0f
        val expectedScale = 1f

        val degrees15 = 15f
        val expectedScale15 = 1.26f

        val degrees30 = 30f
        val expectedScale30 = 1.5f

        assertEquals(expectedScale, getScale(degrees))
        assertEquals(expectedScale15, getScale(degrees15))
        assertEquals(expectedScale30, getScale(degrees30))
    }

    private fun getScale(degrees: Float) = 1 + sin(degrees % 90)
}