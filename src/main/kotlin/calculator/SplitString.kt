package calculator

object SplitString {
    private const val DELIMITER_UNIT_ONE = ","
    private const val DELIMITER_UNIT_SECOND = ":"
    private val customRegex = Regex("//(.)\n(.*)")

    fun split(text: String): List<String> {
        val customList = splitCustom(text)
        return when {
            customList.isNullOrEmpty() -> splitDefault(text)
            else -> customList.flatMap { splitDefault(it) }
        }
    }

    private fun splitDefault(text: String): List<String> = text.split(
        DELIMITER_UNIT_ONE, DELIMITER_UNIT_SECOND
    )

    private fun splitCustom(text: String): List<String> {
        customRegex.find(text)?.let {
            val customDelimiter = it.groupValues[1]
            return it.groupValues[2].split(customDelimiter)
        } ?: return emptyList()
    }
}
