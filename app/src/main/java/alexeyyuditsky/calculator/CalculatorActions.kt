package alexeyyuditsky.calculator

interface CalculatorActions {

    fun clickZero()
    fun clickOne()
    fun clickTwo()

    fun clickPlus()
    fun clickEquals()

    data object Empty : CalculatorActions {
        override fun clickZero() {}
        override fun clickOne() {}
        override fun clickTwo() {}
        override fun clickPlus() {}
        override fun clickEquals() {}
    }
}