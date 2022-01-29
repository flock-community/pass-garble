package community.flock.passgarble.common

actual object PasswordGeneratorFactory {
    actual fun createGenerator(): CommonPasswordGenerator {
//        return PasswordGenerator(Instant.now().epochSecond)
        return CommonPasswordGenerator(-1L)
    }
}
