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

    @Test
    fun sum_of_two_numbers_corner_case() {
        val inputFlow = viewModel.inputFlow
        val resultFlow = viewModel.resultFlow

        viewModel.clickOne()
        assertEquals("1", inputFlow.value)

        var expected = "1"
        repeat(9) {
            viewModel.clickZero()
            expected += 0
            assertEquals(expected, inputFlow.value)
        }
        assertEquals("1000000000", inputFlow.value)

        viewModel.clickPlus()
        assertEquals("1000000000+", inputFlow.value)

        viewModel.clickTwo()
        assertEquals("1000000000+2", inputFlow.value)

        expected = "1000000000+2"
        repeat(9) {
            viewModel.clickZero()
            expected += 0
            assertEquals(expected, inputFlow.value)
        }
        assertEquals("1000000000+2000000000", inputFlow.value)

        viewModel.clickEquals()
        assertEquals("1000000000+2000000000", inputFlow.value)
        assertEquals("3000000000", resultFlow.value)
    }
}