package community.flock.passgarble.common

import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalUnsignedTypes::class)
@Throws(SecureRandomException::class, CancellationException::class)
expect suspend fun getSecureRandomBytes(length: Int): UByteArray

// Common
enum class LogLevel {
    DEBUG, WARN, ERROR
}

internal expect fun writeLogMessage(message: String, logLevel: LogLevel)

fun logDebug(message: String) = writeLogMessage(message, LogLevel.DEBUG)
fun logWarn(message: String) = writeLogMessage(message, LogLevel.WARN)
fun logError(message: String) = writeLogMessage(message, LogLevel.ERROR)
