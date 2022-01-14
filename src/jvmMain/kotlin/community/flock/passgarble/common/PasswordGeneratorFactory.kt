package community.flock.passgarble.common

import java.time.Instant

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): PasswordGenerator {
//        return PasswordGenerator(Instant.now().epochSecond)
        return PasswordGenerator(-1L)
    }
}
