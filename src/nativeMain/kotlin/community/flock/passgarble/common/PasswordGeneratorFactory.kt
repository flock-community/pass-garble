package community.flock.passgarble.common

import kotlinx.datetime.Clock

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): CommonPasswordGenerator {
        return CommonPasswordGenerator(Clock.System.now().toEpochMilliseconds())
    }
}
