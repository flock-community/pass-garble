import community.flock.passgarble.common.CommonPasswordGenerationOptions
import community.flock.passgarble.common.CommonPasswordGenerator
import community.flock.passgarble.common.PasswordGeneratorFactory

class PassGarble() {
    private val passwordGenerator = PasswordGeneratorFactory.createGenerator();

    fun defaultSpecialChars() = CommonPasswordGenerator.defaultSpecialChars
    fun defaultOptions() = CommonPasswordGenerationOptions()

    fun generate(generationOptions: CommonPasswordGenerationOptions
    ): String = passwordGenerator.generate(generationOptions)

    fun generate(
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
