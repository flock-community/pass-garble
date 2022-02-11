package community.flock.passgarble.common

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

internal class PasswordGeneratorTest {
    @Test
    fun generateFunctionIsReproducibleForInstancesUsingTheSameSeed() {
        val generator = CommonPasswordGenerator(-1)
        assertEquals("{S?9q27?!U,bX[F+iMLROcM`fk67e>s", generator.generate())
        assertEquals("l`oKFBWNj!a{3MU3</.[}au(R1pk-)F", generator.generate())

        val generator2 = CommonPasswordGenerator(-1)
        assertEquals("{S?9q27?!U,bX[F+iMLROcM`fk67e>s", generator2.generate())
        assertEquals("l`oKFBWNj!a{3MU3</.[}au(R1pk-)F", generator2.generate())
    }

    @Test
    fun whenUsingOnlyNumbers_passwordIsNumberOnly() {
        val generator = CommonPasswordGenerator(-1)
        assertEquals(
            "6411649106",
            generator.generate(
                passwordLength = 10,
                includeNumbers = true,
                includeUpperCase = false,
                includeLowerCase = false,
                includeSpecialChars = false,
            )
        )
        assertEquals(
            "9194198873", generator.generate(
                passwordLength = 10,
                includeNumbers = true,
                includeUpperCase = false,
                includeLowerCase = false,
                includeSpecialChars = false,
            )
        )

    }

    @Test
    fun whenUsingOnlyUpperAndLowerCase_passwordIsNumberOnly() {
        val generator = CommonPasswordGenerator(-1)
        assertEquals(
            "6411649106",
            generator.generate(
                passwordLength = 10,
                includeNumbers = true,
                includeUpperCase = false,
                includeLowerCase = false,
                includeSpecialChars = false,
            )
        )
        assertEquals(
            "9194198873", generator.generate(
                passwordLength = 10,
                includeNumbers = true,
                includeUpperCase = false,
                includeLowerCase = false,
                includeSpecialChars = false,
            )
        )

    }

    @Test
    fun whenNoCategoriesRequested_IllegalArgumentIsThrown() {
        val options = CommonPasswordGenerationOptions(
            passwordLength = 10,
            includeNumbers = false,
            includeUpperCase = false,
            includeLowerCase = false,
            includeSpecialChars = false
        )
        val generator = CommonPasswordGenerator(-1)
        val exception = assertFails { generator.generate(options) }

        // Verify exception is hierarchically a runtime exception
        assertTrue { exception is RuntimeException }

        // Verify exception is exactly an IllegalArgumentException
        assertEquals(IllegalArgumentException::class, exception::class)

    }
    @Test
    fun whenASingleCategoryIsRequested_passwordIsLimited() {
        val emptyPassword = CommonPasswordGenerationOptions(
            passwordLength = 10,
            includeNumbers = false,
            includeUpperCase = false,
            includeLowerCase = false,
            includeSpecialChars = false
        )

        val testCases = mapOf(
            emptyPassword.copy(includeLowerCase = true) to "eudbwibxgy",
            emptyPassword.copy(includeUpperCase = true) to "EUDBWIBXGY",
            emptyPassword.copy(includeNumbers = true) to "6411649106",
            emptyPassword.copy(includeSpecialChars = true) to "/;<%(!&#>{",
            emptyPassword.copy(includeSpecialChars = true, specialCharSet = listOf('%', '#', '!')) to "%!%##%!%!#",
        )

        testCases.forEach { (options, expected) ->
            val generator = CommonPasswordGenerator(-1)
            assertEquals(expected, generator.generate(options))
        }
    }

}
