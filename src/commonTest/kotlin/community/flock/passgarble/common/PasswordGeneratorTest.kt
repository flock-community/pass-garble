package community.flock.passgarble.common

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class PasswordGeneratorTest {

    @Test
    fun whenUsingOnlyNumbers_passwordIsNumberOnly() = runTest {
        val generator = CommonPasswordGenerator()

        val generatedPassword = generator.generate(
            passwordLength = 10,
            includeNumbers = true,
            includeUpperCase = false,
            includeLowerCase = false,
            includeSpecialChars = false,
        )

        val regex = """\d{10}""".toRegex()
        assertTrue(regex.containsMatchIn(generatedPassword))
    }

    @Test
    fun whenUsingOnlyUpperAndLowerCase_passwordIsAsSuch() = runTest {
        val generator = CommonPasswordGenerator()
        val generatedPassword = generator.generate(
            passwordLength = 10,
            includeNumbers = false,
            includeUpperCase = true,
            includeLowerCase = true,
            includeSpecialChars = false,
        )

        val regex = """[A-z]{10}""".toRegex()
        assertTrue(regex.containsMatchIn(generatedPassword))
    }

    @Test
    fun whenNoCategoriesRequested_IllegalArgumentIsThrown() = runTest  {
        val options = CommonPasswordGenerationOptions(
            passwordLength = 10,
            includeNumbers = false,
            includeUpperCase = false,
            includeLowerCase = false,
            includeSpecialChars = false
        )
        val generator = CommonPasswordGenerator()
        val exception = assertFails { generator.generate(options) }

        // Verify exception is hierarchically a runtime exception
        assertTrue { exception is RuntimeException }

        // Verify exception is exactly an IllegalArgumentException
        assertEquals(IllegalArgumentException::class, exception::class)

    }
    @Test
    fun whenASingleCategoryIsRequested_passwordIsLimited() = runTest  {
        val emptyPassword = CommonPasswordGenerationOptions(
            passwordLength = 10,
            includeNumbers = false,
            includeUpperCase = false,
            includeLowerCase = false,
            includeSpecialChars = false
        )

        val testCases = mapOf(
            emptyPassword.copy(includeLowerCase = true) to """[a-z]{10}""",
            emptyPassword.copy(includeUpperCase = true) to """[A-Z]{10}""",
            emptyPassword.copy(includeNumbers = true) to """[0-9]{10}""",
            emptyPassword.copy(includeSpecialChars = true, specialCharSet = listOf('%', '#', '!')) to """[%#!]{10}""",
        )

        testCases.forEach { (options, expected) ->
            val generator = CommonPasswordGenerator()
            assertTrue(expected.toRegex().containsMatchIn(generator.generate(options)),"$options was not $expected")

        }
    }

}
