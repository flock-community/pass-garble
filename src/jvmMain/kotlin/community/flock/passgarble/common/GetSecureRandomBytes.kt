package community.flock.passgarble.common

import java.security.SecureRandom

private val random = SecureRandom()

// Common
@OptIn(ExperimentalUnsignedTypes::class)
actual suspend fun getSecureRandomBytes(length: Int): UByteArray {
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return bytes.toUByteArray()
}

