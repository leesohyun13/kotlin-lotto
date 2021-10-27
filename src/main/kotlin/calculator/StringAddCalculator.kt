package calculator

class StringAddCalculator {
    fun add(text: String?): Int {
        if (text.isNullOrEmpty()) return RETURN_NULL_OR_EMPTY_STRING
        return SplitString.split(text).sumOf { validationInt(it) }
    }

    private fun validationInt(text: String): Int {
        return text.toIntOrNull() ?: throw RuntimeException(EXCEPTION_NULL_OR_EMPTY)
    }

    companion object {
        private const val RETURN_NULL_OR_EMPTY_STRING = 0
        private const val EXCEPTION_NULL_OR_EMPTY = "숫자가 아니거나 음수 입니다."
    }
}
