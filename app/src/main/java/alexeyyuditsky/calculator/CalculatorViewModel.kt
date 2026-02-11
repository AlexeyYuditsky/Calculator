package alexeyyuditsky.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel : ViewModel(), CalculatorActions {

    private val inputMutableFlow = MutableStateFlow("")
    private val resultMutableFlow = MutableStateFlow("")

    val inputFlow get() = inputMutableFlow.asStateFlow()
    val resultFlow get() = resultMutableFlow.asStateFlow()

    private var left = 0
    private var right = 0

    override fun clickOne() {
        left = 1
        inputMutableFlow.value = left.toString()
    }

    override fun clickTwo() {
        right = 2
        inputMutableFlow.value = "$left+$right"
    }

    override fun clickPlus() {
        inputMutableFlow.value = "$left+"
    }

    override fun clickEquals() {
        resultMutableFlow.value = (left + right).toString()
    }
}