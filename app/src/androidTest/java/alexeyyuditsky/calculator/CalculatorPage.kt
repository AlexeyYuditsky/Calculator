package alexeyyuditsky.calculator

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule

class CalculatorPage(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {

    private val one = composeTestRule.onNode(
        hasTestTag("one") and hasText("1") and hasClickAction()
    )
    private val two = composeTestRule.onNode(
        hasTestTag("two") and hasText("2") and hasClickAction()
    )
    private val plus = composeTestRule.onNode(
        hasTestTag("plus") and hasText("+") and hasClickAction()
    )
    private val equals = composeTestRule.onNode(
        hasTestTag("equals") and hasText("=") and hasClickAction()
    )
    private val input = composeTestRule.onNode(
        hasTestTag("input") and hasNoClickAction()
    )
    private val result = composeTestRule.onNode(
        hasTestTag("result") and hasNoClickAction()
    )

    fun clickOne() = one.performClick()

    fun clickTwo() = two.performClick()

    fun clickPlus() = plus.performClick()

    fun clickEquals() = equals.performClick()

    fun assertInput(expected: String) = input.assertTextEquals(expected)

    fun assertResult(expected: String) = result.assertTextEquals(expected)
}