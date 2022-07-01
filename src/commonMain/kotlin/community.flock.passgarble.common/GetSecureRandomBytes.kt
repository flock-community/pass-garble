package community.flock.passgarble.common

import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalUnsignedTypes::class)
@Throws(SecureRandomException::class, CancellationException::class)
expect suspend fun getSecureRandomBytes(length: Int): UByteArray
