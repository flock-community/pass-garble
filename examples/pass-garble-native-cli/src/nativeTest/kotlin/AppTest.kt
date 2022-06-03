import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun appHasAGreeting() {
//        val classUnderTest = App()
        assertNotNull(main(), "app should have a greeting")
    }
}
