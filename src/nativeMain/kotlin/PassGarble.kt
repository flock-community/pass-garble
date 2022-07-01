import community.flock.passgarble.common.CommonPasswordGenerationOptions
import community.flock.passgarble.common.CommonPasswordGenerator

class PassGarble() {
    fun defaultSpecialChars() = CommonPasswordGenerator.defaultSpecialChars
    fun defaultOptions() = CommonPasswordGenerationOptions()

    suspend fun generate(
        generationOptions: CommonPasswordGenerationOptions
    ): String = CommonPasswordGenerator.generate(generationOptions)

    suspend fun generate(
        passwordLength: Int,
        includeLowerCase: Boolean,
        includeUpperCase: Boolean,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        specialCharsSet: List<Char>
    ): String {
        return CommonPasswordGenerator.generate(
            passwordLength = passwordLength,
            includeLowerCase = includeLowerCase,
            includeUpperCase = includeUpperCase,
            includeNumbers = includeNumbers,
            includeSpecialChars = includeSpecialChars,
            specialCharsSet = specialCharsSet
        )
    }
}
