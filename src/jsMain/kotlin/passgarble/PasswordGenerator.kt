@file:OptIn(ExperimentalJsExport::class)
package passgarble

import community.flock.passgarble.common.CommonPasswordGenerationOptions
import community.flock.passgarble.common.CommonPasswordGenerator
import community.flock.passgarble.common.PasswordGeneratorFactory


@JsExport
class PasswordGenerator() {
    private val generator: CommonPasswordGenerator = PasswordGeneratorFactory.createGenerator();

    fun generatePassword(options: PasswordGenerationOptions = defaultOptions()): String {
        return generator.generate(
            passwordLength = options.passwordLength,
            includeLowerCase = options.includeLowerCase,
            includeUpperCase = options.includeUpperCase,
            includeNumbers = options.includeNumbers,
            includeSpecialChars = options.includeSpecialChars,
            specialCharsSet = options.specialCharSet.toCharArray().toList(),
        )
    }
}

val commonDefaultOptions = CommonPasswordGenerationOptions()

@JsExport
data class PasswordGenerationOptions(
    val passwordLength: Int = commonDefaultOptions.passwordLength,
    val includeLowerCase: Boolean = commonDefaultOptions.includeLowerCase,
    val includeUpperCase: Boolean = commonDefaultOptions.includeUpperCase,
    val includeNumbers: Boolean = commonDefaultOptions.includeNumbers,
    val includeSpecialChars: Boolean = commonDefaultOptions.includeSpecialChars,
    val specialCharSet: String = commonDefaultOptions.specialCharSet.joinToString("")
)

@JsExport
fun defaultOptions() = PasswordGenerationOptions()

