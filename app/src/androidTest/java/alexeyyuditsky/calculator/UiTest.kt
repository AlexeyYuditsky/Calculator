package alexeyyuditsky.calculator

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val calculatorPage = CalculatorPage(composeTestRule)

    @Test
    fun sum_of_two_numbers() {
        calculatorPage.clickOne()
        calculatorPage.assertInput(expected = "1")

        calculatorPage.clickPlus()
        calculatorPage.assertInput(expected = "1+")

        calculatorPage.clickTwo()
        calculatorPage.assertInput(expected = "1+2")

        calculatorPage.clickEquals()
        calculatorPage.assertInput(expected = "1+2")
        calculatorPage.assertResult(expected = "3")
    }

    @Test
    fun sum_of_two_numbers_corner_case() {
        calculatorPage.clickOne()
        calculatorPage.assertInput(expected = "1")

        var expected = "1"
        repeat(9) {
            calculatorPage.clickZero()
            expected += 0
            calculatorPage.assertInput(expected = expected)
        }
        calculatorPage.assertInput(expected = "1000000000")

        calculatorPage.clickPlus()
        calculatorPage.assertInput(expected = "1000000000+")

        calculatorPage.clickTwo()
        calculatorPage.assertInput(expected = "1000000000+2")

        expected = "1000000000+2"
        repeat(9) {
            calculatorPage.clickZero()
            expected += 0
            calculatorPage.assertInput(expected = expected)
        }
        calculatorPage.assertInput(expected = "1000000000+2000000000")

        calculatorPage.clickEquals()
        calculatorPage.assertInput(expected = "1000000000+2000000000")
        calculatorPage.assertResult(expected = "3000000000")
    }

    @Test
    fun prevent_multiple_zeros() {
        calculatorPage.clickZero()
        calculatorPage.assertInput(expected = "0")

        calculatorPage.clickZero()
        calculatorPage.assertInput(expected = "0")
    }
}