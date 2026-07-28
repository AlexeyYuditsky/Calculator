package alexeyyuditsky.calculator

enum class Operation {
    EMPTY {
        override fun toString(): String = ""
    },
    PLUS {
        override fun toString(): String = "+"
    },
    MINUS {
        override fun toString(): String = "-"
    },
    MULTIPLE {
        override fun toString(): String = "*"
    },
    DIVIDE {
        override fun toString(): String = ":"
    }
}