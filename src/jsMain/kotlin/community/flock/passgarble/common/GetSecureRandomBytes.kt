@file:OptIn(ExperimentalUnsignedTypes::class)

package community.flock.passgarble.common

import com.ionspin.kotlin.crypto.LibsodiumInitializer
import com.ionspin.kotlin.crypto.util.LibsodiumRandom


actual suspend fun getSecureRandomBytes(length: Int): UByteArray {
    if (!LibsodiumInitializer.isInitialized()) {
        init()
    }
    return doGetRandomBytes(length)
}

private fun doGetRandomBytes(length: Int): UByteArray {
    val array = LibsodiumRandom.buf(length)
    return array.toUByteArray()
}


private suspend fun init() {
    // Init libsodium, cause reasons: https://github.com/ionspin/kotlin-multiplatform-libsodium#usage
    LibsodiumInitializer.initialize()
}

internal actual fun writeLogMessage(
    message: String,
    logLevel: LogLevel
) = when (logLevel) {
    LogLevel.DEBUG -> console.log(message)
    LogLevel.WARN -> console.warn(message)
    LogLevel.ERROR -> console.error(message)
}

