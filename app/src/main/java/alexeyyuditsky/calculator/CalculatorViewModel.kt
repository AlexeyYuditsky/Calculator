package alexeyyuditsky.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigInteger

class CalculatorViewModel : ViewModel(), CalculatorActions {

    private val inputMutableFlow = MutableStateFlow("")
    private val resultMutableFlow = MutableStateFlow("")

    val inputFlow get() = inputMutableFlow.asStateFlow()
    val resultFlow get() = resultMutableFlow.asStateFlow()

    private var addToLeft = true
    private var left = ""
    private var right = ""

    override fun clickZero() {
        if (addToLeft) {
            left += "0"
            inputMutableFlow.value = left
        } else {
            right += "0"
            inputMutableFlow.value = "$left+$right"
        }
    }

    override fun clickOne() {
        left += "1"
        inputMutableFlow.value = left
    }

    override fun clickTwo() {
        right += "2"
        inputMutableFlow.value = "$left+$right"
    }

    override fun clickPlus() {
        addToLeft = false
        inputMutableFlow.value = "${inputFlow.value}+"
    }

    override fun clickEquals() {
        resultMutableFlow.value = BigInteger(left).plus(BigInteger(right)).toString()
    }
}