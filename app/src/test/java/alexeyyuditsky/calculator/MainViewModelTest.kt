package alexeyyuditsky.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    private val viewModel = CalculatorViewModel()

    @Test
    fun sum_of_two_numbers() {
        val inputFlow = viewModel.inputFlow
        val resultFlow = viewModel.resultFlow

        assertEquals("", inputFlow.value)
        assertEquals("", resultFlow.value)

        viewModel.clickOne()
        assertEquals("1", inputFlow.value)

        viewModel.clickPlus()
        assertEquals("1+", inputFlow.value)

        viewModel.clickTwo()
        assertEquals("1+2", inputFlow.value)

        viewModel.clickEquals()
        assertEquals("1+2", inputFlow.value)
        assertEquals("3", resultFlow.value)
    }
}