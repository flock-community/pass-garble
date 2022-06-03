package community.flock.passgarble.cli

import community.flock.passgarble.common.CommonPasswordGenerator
import community.flock.passgarble.common.createGenerator
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(
    name = "pass-garble",
    mixinStandardHelpOptions = true,
    version = ["0.0.2"],
    description = ["A password generator for all your passwords"]
)
class PassGarbleCli : Callable<Int> {
    private val passwordGenerator: CommonPasswordGenerator = createGenerator()
    private val defaultOptions = CommonPasswordGenerator.defaultOptions()

    @Option(
        names = ["-l", "--length"],
        showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
        description = ["the number of characters to be used for the password."]
    )
    var length: Int = defaultOptions.passwordLength

    @Option(
        names = ["--lower", "--lowercase"],
        showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
        description = ["whether to include lowercase letters in the passwords"]
    )
    var useLowercaseCharacters: Boolean = defaultOptions.includeLowerCase

    @Option(
        names = ["--upper", "--uppercase"],
        showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
        description = ["whether to include uppercase letters in the passwords"]
    )
    var useUppercaseCharacters: Boolean = defaultOptions.includeUpperCase

    @Option(
        names = ["--numbers"],
        showDefaultValue = CommandLine.Help.Visibility.ALWAYS,

        description = ["whether to include number in the passwords"]
    )
    var useNumbers: Boolean = defaultOptions.includeNumbers

    @Option(
        names = ["--special"],
        showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
        description = ["whether to include special characters in the passwords"]
    )
    var useSpecialCharacters: Boolean = defaultOptions.includeSpecialChars

    override fun call(): Int {
        println("Running Pass garble CLI")
        println("Selected options:")
        println("  - password length         : $length")
        println("  - use lowercase characters: $useLowercaseCharacters")
        println("  - use uppercase characters: $useUppercaseCharacters")
        println("  - use numbers             : $useNumbers")
        println("  - use special characters  : $useSpecialCharacters")
        println()

        val generatedPassword = passwordGenerator.generate(
            passwordLength = length,
            includeLowerCase = useLowercaseCharacters,
            includeUpperCase = useUppercaseCharacters,
            includeNumbers = useNumbers, includeSpecialChars = useSpecialCharacters
        )


        println("### Password ###")
        println(generatedPassword)
        return 0;
    }
}

fun main(args: Array<String>): Unit = exitProcess(CommandLine(PassGarbleCli()).execute(*args))
