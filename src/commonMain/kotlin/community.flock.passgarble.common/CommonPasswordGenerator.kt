package community.flock.passgarble.common

import kotlin.random.Random

class CommonPasswordGenerator(seed: Long) {
    private val random = Random(seed)

    fun generate(
        passwordLength: Int = 31,
        includeLowerCase: Boolean = true,
        includeUpperCase: Boolean = true,
        includeOAndNulls: Boolean = true,
        includeSpecialChars: Boolean = true,
        specialCharsSet: List<Char> = defaultSpecialChars
    ): String {
        val charPool: List<Char> =
            lowerCase.fullOrEmpty(includeLowerCase) +
                    upperCase.fullOrEmpty(includeUpperCase) +
                    zerosAndOs.fullOrEmpty(includeOAndNulls) +
                    specialCharsSet.fullOrEmpty(includeSpecialChars)

        return (1..passwordLength)
            .map { random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("");
    }

    private infix fun List<Char>.fullOrEmpty(shouldInclude: Boolean): List<Char> =
        this fullOrEmpty { shouldInclude }

    private infix fun List<Char>.fullOrEmpty(block: () -> Boolean): List<Char> = when {
        block() -> this
        else -> emptyList()
    }

    companion object {

        private val lowerCase: List<Char> = ('a'..'z').toList()
        private val upperCase: List<Char> = ('A'..'Z').toList()
        private val zerosAndOs: List<Char> = "Oo0".asCharList()

        // Special chars
        val brackets: List<Char> = "()[]{}".asCharList()
        val maths: List<Char> = "*+-=/<>".asCharList()
        val quotes: List<Char> = "'\"`".asCharList()
        val punctuation: List<Char> = ".,!?;:".asCharList()
        val others: List<Char> = "@#$%^&".asCharList()

        private fun String.asCharList(): List<Char> = toCharArray().toList()

        val defaultSpecialChars = (brackets + maths + quotes + punctuation + others)
    }
}


expect object PasswordGeneratorFactory {
    fun createGenerator(): CommonPasswordGenerator
}
