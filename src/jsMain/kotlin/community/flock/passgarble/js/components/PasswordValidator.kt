package community.flock.passwordgarble.components

import kotlinx.browser.window
import kotlinx.html.InputType
import kotlinx.html.role
import react.Props
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.form
import react.dom.h3
import react.dom.input
import react.dom.onSubmit
import react.fc

val passwordValidator = fc<Props> {
    // typesafe HTML goes here!
    div {
        h3 {
            +"Validate a password"
        }
        form {
            attrs {
//                onSubmit = {
//                    window.alert("You're validating '${this["password"].value}'")
//                }
            }
            input(type = InputType.text, name = "password") {
                attrs {
                    placeholder = "Type a password ..."
                }
            }
            button {
                attrs{
                    role = "submit"
                }
                +"Validate"
            }
        }
    }
}
