package community.flock.passgarble.common

import kotlin.random.Random

class PasswordGenerator(seed: Long) {
    private val random = Random(seed)

    //
    //TODO: allow for configurable passwords:
    // - length
    // - chars to use (
    //      char groups probably
    //      [A-Z],
    //      [a-z],
    //      [0-9],
    //      [!@#$%^&*()<>?:"|{}[];'\,./] or subsets of this,
    //      [éë...] and such?
    fun generate(): String {
        var pass = ""
        repeat(50) {
            val nextChar = random.nextInt(35, 126)
            pass += Char(nextChar).toString()
        }

        return pass
    }
}


expect object PasswordGeneratorFactory {
    fun createGenerator(): PasswordGenerator
}
