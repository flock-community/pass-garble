package community.flock.passgarble.common

import kotlin.js.Date

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): PasswordGenerator {
//        return PasswordGenerator(Date.now().toLong())
        return PasswordGenerator(-1L)
    }
}
