package community.flock.passgarble.common

import java.time.Instant

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): CommonPasswordGenerator {
        return CommonPasswordGenerator(Instant.now().epochSecond)
    }
}
