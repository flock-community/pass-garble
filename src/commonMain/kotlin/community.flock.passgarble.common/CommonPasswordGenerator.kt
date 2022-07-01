package community.flock.passgarble.common

import kotlin.math.floor

/**
 * A password generator, that allows one to generate configurable passwords.
 */
class CommonPasswordGenerator {

    companion object {

        fun defaultOptions() = CommonPasswordGenerationOptions()

        private val lowerCase: List<Char> = ('a'..'z').toList()
        private val upperCase: List<Char> = ('A'..'Z').toList()
        private val numbers: List<Char> = ('0'..'9').toList()

        // Special chars
        private val brackets: List<Char> = "()[]{}".asCharList()
        private val maths: List<Char> = "*+-=/<>".asCharList()
        private val quotes: List<Char> = "'\"`".asCharList()
        private val punctuation: List<Char> = ".,!?;:".asCharList()
        private val others: List<Char> = "@#$%^&".asCharList()

        private fun String.asCharList(): List<Char> = toCharArray().toList()

        val defaultSpecialChars = (brackets + maths + quotes + punctuation + others)

        suspend fun generate(
            passwordLength: Int = 31,
            includeLowerCase: Boolean = true,
            includeUpperCase: Boolean = true,
            includeNumbers: Boolean = true,
            includeSpecialChars: Boolean = true,
            specialCharsSet: List<Char> = defaultSpecialChars
        ): String {

            if (!(includeLowerCase || includeUpperCase || includeNumbers || includeSpecialChars)) {
                throw IllegalArgumentException("Choose at least one category of characters")
            } else if (includeSpecialChars && specialCharsSet.isEmpty()) {
                throw IllegalArgumentException("Cannot include special characters if the specialCharSet is empty")
            }

            // Determine character pool
            val charPool: List<Char> =
                lowerCase.fullOrEmpty(includeLowerCase) +
                        upperCase.fullOrEmpty(includeUpperCase) +
                        numbers.fullOrEmpty(includeNumbers) +
                        specialCharsSet.fullOrEmpty(includeSpecialChars)

            return (0 until passwordLength)
                .map { nextRandomCharacter(charPool) }
                .joinToString("")
        }

        suspend fun generate(
            generationOptions: CommonPasswordGenerationOptions
        ): String = generate(
            passwordLength = generationOptions.passwordLength,
            includeLowerCase = generationOptions.includeLowerCase,
            includeUpperCase = generationOptions.includeUpperCase,
            includeNumbers = generationOptions.includeNumbers,
            includeSpecialChars = generationOptions.includeSpecialChars,
            specialCharsSet = generationOptions.specialCharSet
        )

        private suspend fun nextRandomCharacter(charPool: List<Char>): Char =
            charPool[getRandomInt(charPool.indices)]

        @OptIn(ExperimentalUnsignedTypes::class)
        private suspend fun getRandomInt(xrange: IntRange): Int {
            // Create byte array and fill with 1 random number
            var byteArray: UByteArray

            val range = xrange.last - xrange.first + 1
            val maxRange = 256
            do {
                byteArray = getSecureRandomBytes(1)
            } while (byteArray[0].toInt() >= floor(maxRange * 1.0 / range) * range)

            return xrange.first + (byteArray[0].toInt() % range)
        }
    }
}

private infix fun List<Char>.fullOrEmpty(shouldInclude: Boolean): List<Char> =
    when {
        shouldInclude -> this
        else -> emptyList()
    }


