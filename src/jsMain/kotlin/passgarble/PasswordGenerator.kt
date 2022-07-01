@file:OptIn(ExperimentalJsExport::class)

package passgarble

import community.flock.passgarble.common.CommonPasswordGenerationOptions
import community.flock.passgarble.common.CommonPasswordGenerator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
class PasswordGenerator() {
    @OptIn(DelicateCoroutinesApi::class)
    fun generatePassword(options: PasswordGenerationOptions = defaultOptions()): Promise<String> =
        GlobalScope.promise { generate(options) }

    private suspend fun generate(options: PasswordGenerationOptions): String = CommonPasswordGenerator.generate(
        passwordLength = options.passwordLength,
        includeLowerCase = options.includeLowerCase,
        includeUpperCase = options.includeUpperCase,
        includeNumbers = options.includeNumbers,
        includeSpecialChars = options.includeSpecialChars,
        specialCharsSet = options.specialCharSet.toCharArray().toList(),
    )
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
@JsName("defaultOptions")
fun defaultOptions() = PasswordGenerationOptions()
