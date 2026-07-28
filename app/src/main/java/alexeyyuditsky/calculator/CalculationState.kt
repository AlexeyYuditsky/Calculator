package alexeyyuditsky.calculator

interface CalculationState {

    fun handleNumber(
        number: String,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    )

    fun handleZero(
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    )

    fun handlePlus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    )

    fun handleMinus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    )

    fun handleEquals(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    ) = Unit

    fun handleClear(
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback,
    ) {
        callback.updateCalculationParts(CalculationParts())
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }
}

class DefiningLeftPart : CalculationState {

    override fun handleNumber(
        number: String,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.left == "0") {
            callback.updateCalculationParts(CalculationParts(left = number))
        } else {
            callback.updateCalculationParts(CalculationParts(left = calculationParts.left + number))
        }
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleZero(
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.left == "0" || calculationParts.left == "-") return
        callback.updateCalculationParts(CalculationParts(left = calculationParts.left + "0"))
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handlePlus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.left.isEmpty()) return
        if (calculationParts.left == "-") {
            callback.updateCalculationParts(CalculationParts())
        } else {
            callback.updateCalculationParts(
                CalculationParts(left = calculationParts.left, operation = Operation.PLUS)
            )
            callback.updateCalculationState(DefiningOperationPart())
        }
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleMinus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.left == "-") return
        if (calculationParts.left.isEmpty()) {
            callback.updateCalculationParts(CalculationParts(left = "-"))
        } else {
            callback.updateCalculationParts(
                CalculationParts(left = calculationParts.left, operation = Operation.MINUS)
            )
            callback.updateCalculationState(DefiningOperationPart())
        }
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }
}

class DefiningOperationPart : CalculationState {

    override fun handleNumber(
        number: String,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        callback.updateCalculationParts(
            CalculationParts(
                left = calculationParts.left,
                operation = calculationParts.operation,
                right = number
            )
        )
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
        callback.updateCalculationState(DefiningRightPart())
    }

    override fun handleZero(
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) = handleNumber("0", calculationParts, callback)

    override fun handlePlus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.operation == Operation.PLUS) return
        callback.updateCalculationParts(
            CalculationParts(left = calculationParts.left, operation = Operation.PLUS)
        )
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleMinus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.operation == Operation.MINUS) return
        callback.updateCalculationParts(
            CalculationParts(left = calculationParts.left, operation = Operation.MINUS)
        )
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }
}

class DefiningRightPart : CalculationState {

    override fun handleNumber(
        number: String,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.right == "0") {
            callback.updateCalculationParts(
                CalculationParts(
                    left = calculationParts.left,
                    operation = calculationParts.operation,
                    right = number,
                )
            )
        } else {
            callback.updateCalculationParts(
                CalculationParts(
                    left = calculationParts.left,
                    operation = calculationParts.operation,
                    right = calculationParts.right + number,
                )
            )
        }
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleZero(
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        if (calculationParts.right == "0") return
        callback.updateCalculationParts(
            CalculationParts(
                left = calculationParts.left,
                operation = calculationParts.operation,
                right = calculationParts.right + "0",
            )
        )
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handlePlus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        val result = when (calculationParts.operation) {
            Operation.PLUS -> calculator.sum(calculationParts.left, calculationParts.right)
            Operation.MINUS -> calculator.diff(calculationParts.left, calculationParts.right)
            else -> TODO()
        }
        callback.updateCalculationParts(CalculationParts(left = result, operation = Operation.PLUS))
        callback.updateCalculationState(DefiningOperationPart())
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleMinus(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        val result = when (calculationParts.operation) {
            Operation.PLUS -> calculator.sum(calculationParts.left, calculationParts.right)
            Operation.MINUS -> calculator.diff(calculationParts.left, calculationParts.right)
            else -> TODO()
        }
        callback.updateCalculationParts(
            CalculationParts(left = result, operation = Operation.MINUS)
        )
        callback.updateCalculationState(DefiningOperationPart())
        callback.updateCalculationInput()
        callback.updateCalculationResult("")
    }

    override fun handleEquals(
        calculator: Calculator,
        calculationParts: CalculationParts,
        callback: CalculatorUpdateCallback
    ) {
        val result = when (calculationParts.operation) {
            Operation.PLUS -> calculator.sum(calculationParts.left, calculationParts.right)
            Operation.MINUS -> calculator.diff(calculationParts.left, calculationParts.right)
            else -> TODO()
        }
        callback.updateCalculationParts(CalculationParts(left = result))
        callback.updateCalculationState(DefiningLeftPart())
        callback.updateCalculationResult(result)
    }
}