package community.flock.passgarble.common

import kotlinx.cinterop.ByteVarOf
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.refTo
import platform.Security.SecRandomCopyBytes
import platform.Security.errSecSuccess
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalUnsignedTypes::class)
@Throws(SecureRandomException::class, CancellationException::class)
actual suspend fun getSecureRandomBytes(length: Int): UByteArray {
    val byteArray = ByteArray(length)

    val bytesPointer: CValuesRef<ByteVarOf<Byte>> = byteArray.refTo(0)
    val secRandomCopyBytes: Int = SecRandomCopyBytes(null, length.toULong(), bytesPointer)

    if (secRandomCopyBytes == errSecSuccess) {
        return byteArray.toUByteArray()
    } else {
        throw SecureRandomException("Could not securely retrieve $length number of bytes")
    }
}

internal actual fun writeLogMessage(message: String, logLevel: LogLevel) {
}
