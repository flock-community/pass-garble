package community.flock.passgarble.common

import kotlin.js.Date

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): CommonPasswordGenerator {
        return CommonPasswordGenerator(Date.now().toLong())
    }
}
