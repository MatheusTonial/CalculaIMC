package com.tonial.calculaimc

import com.tonial.calculaimc.util.calcularIMC
import org.junit.Test

import org.junit.Assert.*

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
    fun imc_isCorrect() {

        assertTrue(
            "Valor esta correto",
            calcularIMC(90.0, 1.90, "pt") in 24.93 .. 24.94
        )
    }
}