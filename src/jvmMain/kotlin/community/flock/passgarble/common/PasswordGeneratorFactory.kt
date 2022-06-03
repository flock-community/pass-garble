package community.flock.passgarble.common

import java.security.SecureRandom

actual suspend fun getSecureRandomBytes(length: Int): UByteArray {
    val random = SecureRandom()
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return bytes.toUByteArray()
}
