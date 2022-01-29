package community.flock.passgarble.common

import kotlin.test.Test
import kotlin.test.assertEquals

internal class PasswordGeneratorTest {
    @Test
    fun generateFunctionIsReproducibleForInstancesUsingTheSameSeed() {
        assertEquals("47tXF_>:6.P2QX8v:pCYj5(6/I^GImre*qKwbf;,/JfC<Zt,tD", CommonPasswordGenerator(-1).generate())
        assertEquals("47tXF_>:6.P2QX8v:pCYj5(6/I^GImre*qKwbf;,/JfC<Zt,tD", CommonPasswordGenerator(-1).generate())
    }
}
