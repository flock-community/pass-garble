package community.flock.passgarble

import community.flock.passgarble.js.app
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(app)
        }
    }
}
