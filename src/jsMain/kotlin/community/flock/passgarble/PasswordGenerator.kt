@file:OptIn(ExperimentalJsExport::class)

package community.flock.passgarble

import community.flock.passgarble.common.CommonPasswordGenerator
import community.flock.passgarble.common.PasswordGeneratorFactory

@JsExport
class PasswordGenerator() {
    private val generator: CommonPasswordGenerator = PasswordGeneratorFactory.createGenerator();

    fun generatePassword(options: PasswordGenerationOptions = defaultPasswordGenerationOptions): String {
        return generator.generate(
            passwordLength = options.passwordLength,
            includeLowerCase = options.includeLowerCase,
            includeUpperCase = options.includeLowerCase,
            includeOAndNulls = options.includeLowerCase,
            includeSpecialChars = options.includeLowerCase,
            specialCharsSet = options.specialCharSet.toCharArray().toList(),
        )
    }

    //TODO: Do something actually useful, like a simple version of https://github.com/dropbox/zxcvbn
    fun validatePassword(password: String): PasswordStrength =
        if (password.length < 20) PasswordStrength.LAME else PasswordStrength.SUPERB
}

@JsExport
val defaultPasswordGenerationOptions = PasswordGenerationOptions()

@JsExport
data class PasswordGenerationOptions(
    val passwordLength: Int = 31,
    val includeLowerCase: Boolean = true,
    val includeUpperCase: Boolean = true,
    val includeNullsAndCase: Boolean = true,
    val includeSpecialChars: Boolean = true,
    val specialCharSet: String = CommonPasswordGenerator.defaultSpecialChars.joinToString("")
)

@JsExport
sealed class PasswordStrength(private val value:String) {
    object LAME : PasswordStrength("lame")
    object SUPERB : PasswordStrength("superb")
    object UNKNOWN : PasswordStrength("unknown")

    override fun toString(): String {
        return "PasswordStrength[$value]"
    }
}
