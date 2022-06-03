import community.flock.passgarble.common.CommonPasswordGenerationOptions


var passwordGenerationOptions = CommonPasswordGenerationOptions()

fun main() {

    printHelp()
    val x = PassGarble()



    do {
        println(x.generate(passwordGenerationOptions))
        takeNextUserInput()
    } while (true)


}

private fun takeNextUserInput() {
    val userInput = readlnOrNull().takeIf { !it.isNullOrEmpty() }
    userInput?.let {
        processInput(it) }
}

fun printHelp() {
    println("PassGarble - a password generator for all your passwords")

    println(
        """ 
    Current options:
      - password length         : ${passwordGenerationOptions.passwordLength}
      - use lowercase characters: ${if(passwordGenerationOptions.includeLowerCase) "✅" else "❌"}
      - use uppercase characters: ${if(passwordGenerationOptions.includeUpperCase) "✅" else "❌"}
      - use numbers             : ${if(passwordGenerationOptions.includeNumbers) "✅" else "❌"}
      - use special characters  : ${if(passwordGenerationOptions.includeSpecialChars) "✅" else "❌"}
        """.trimIndent()

    )
    println()

    println(
        """
        Commands:
        - length     - update password length
        - u, upper   - toggle uppercase characters
        - l, lower   - toggle lowercase characters
        - n, number  - toggle number characters
        - s, special - toggle special characters
        - h, help    - show help
        
        Example: type "length" (return), to get a request to update the length of the password
        """.trimIndent()
    )
    println()
}

fun processInput(input: String) {
    when (input.trim().lowercase()) {
        "length" -> {
            print("New password length?   ")
            readlnOrNull()?.toIntOrNull()
                ?.let { passwordGenerationOptions = passwordGenerationOptions.copy(passwordLength = it) }
        }
        "l", "lower" -> {
            passwordGenerationOptions =
                passwordGenerationOptions.copy(includeLowerCase = !passwordGenerationOptions.includeLowerCase)
            println("Include lowercase characters  ${if(passwordGenerationOptions.includeLowerCase) "✅" else "❌"}")
        }
        "u", "upper" -> {
            passwordGenerationOptions =
                passwordGenerationOptions.copy(includeUpperCase = !passwordGenerationOptions.includeUpperCase)
            println("Include uppercase characters  ${if(passwordGenerationOptions.includeUpperCase) "✅" else "❌"}")
        }
        "n", "number" -> {
            passwordGenerationOptions =
                passwordGenerationOptions.copy(includeNumbers = !passwordGenerationOptions.includeNumbers)
            println("Include number characters  ${if(passwordGenerationOptions.includeNumbers) "✅" else "❌"}")
        }
        "s", "special" -> {
            passwordGenerationOptions =
                passwordGenerationOptions.copy(includeSpecialChars = !passwordGenerationOptions.includeSpecialChars)
            println("Include special characters  ${if(passwordGenerationOptions.includeSpecialChars) "✅" else "❌"}")
        }
        "h","help" -> {
            println()
            printHelp()
            takeNextUserInput()
        }
        else -> {
            println("I don't understand.Let's continue ...")
        }
    }
}
