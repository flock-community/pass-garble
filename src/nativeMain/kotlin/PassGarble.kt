import community.flock.passgarble.common.CommonPasswordGenerationOptions
import community.flock.passgarble.common.CommonPasswordGenerator

class PassGarble() {
    private val passwordGenerator = CommonPasswordGenerator();

    fun defaultSpecialChars() = CommonPasswordGenerator.defaultSpecialChars
    fun defaultOptions() = CommonPasswordGenerationOptions()

    suspend fun generate(
        generationOptions: CommonPasswordGenerationOptions
    ): String = passwordGenerator.generate(generationOptions)

    suspend fun generate(
        passwordLength: Int,
        includeLowerCase: Boolean,
        includeUpperCase: Boolean,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        specialCharsSet: List<Char>
    ): String {
        return passwordGenerator.generate(
            passwordLength = passwordLength,
            includeLowerCase = includeLowerCase,
            includeUpperCase = includeUpperCase,
            includeNumbers = includeNumbers,
            includeSpecialChars = includeSpecialChars,
            specialCharsSet = specialCharsSet
        )
    }
}
