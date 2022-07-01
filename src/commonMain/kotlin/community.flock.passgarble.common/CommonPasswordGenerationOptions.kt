package community.flock.passgarble.common

data class CommonPasswordGenerationOptions(
    val passwordLength: Int = 31,
    val includeLowerCase: Boolean = true,
    val includeUpperCase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSpecialChars: Boolean = true,
    val specialCharSet: List<Char> = CommonPasswordGenerator.defaultSpecialChars
)

class SecureRandomException(
    override val message: String,
    override val cause: Throwable?
) : RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
