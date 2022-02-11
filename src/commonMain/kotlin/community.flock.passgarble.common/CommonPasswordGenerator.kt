package community.flock.passgarble.common

import kotlin.random.Random

/**
 * A password generator, that allows one to generate configurable passwords.
 *
 * @property seed - provide a seed to initialize the randomizer. Ideally this is a context dependent value
 *      and not a fixed value  as to prevent the same password from being generated
 */
class CommonPasswordGenerator(seed: Long) {
    private val random = Random(seed)

    fun generate(generationOptions: CommonPasswordGenerationOptions
    ): String = generate(
        passwordLength = generationOptions.passwordLength,
        includeLowerCase = generationOptions.includeLowerCase,
        includeUpperCase = generationOptions.includeUpperCase,
        includeNumbers = generationOptions.includeNumbers,
        includeSpecialChars = generationOptions.includeSpecialChars,
        specialCharsSet = generationOptions.specialCharSet
    )

    fun generate(
        passwordLength: Int = 31,
        includeLowerCase: Boolean = true,
        includeUpperCase: Boolean = true,
        includeNumbers: Boolean = true,
        includeSpecialChars: Boolean = true,
        specialCharsSet: List<Char> = defaultSpecialChars
    ): String {
        if (!(includeLowerCase || includeUpperCase || includeNumbers || includeSpecialChars)){
            throw IllegalArgumentException("Choose at least one category of characters")
        } else if (includeSpecialChars && specialCharsSet.isEmpty()){
            throw IllegalArgumentException("Cannot include special characters if the specialCharSet is empty")
        }

        val charPool: List<Char> =
            lowerCase.fullOrEmpty(includeLowerCase) +
                    upperCase.fullOrEmpty(includeUpperCase) +
                    numbers.fullOrEmpty(includeNumbers) +
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
        private val numbers: List<Char> = ('0'..'9').toList()

        // Special chars
        private val brackets: List<Char> = "()[]{}".asCharList()
        private val maths: List<Char> = "*+-=/<>".asCharList()
        private val quotes: List<Char> = "'\"`".asCharList()
        private val punctuation: List<Char> = ".,!?;:".asCharList()
        private val others: List<Char> = "@#$%^&".asCharList()

        private fun String.asCharList(): List<Char> = toCharArray().toList()

        val defaultSpecialChars = (brackets + maths + quotes + punctuation + others)
    }
}


data class CommonPasswordGenerationOptions(
    val passwordLength: Int = 31,
    val includeLowerCase: Boolean = true,
    val includeUpperCase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSpecialChars: Boolean = true,
    val specialCharSet: List<Char> = CommonPasswordGenerator.defaultSpecialChars
)


expect object PasswordGeneratorFactory {
    fun createGenerator(): CommonPasswordGenerator
}
