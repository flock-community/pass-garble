package community.flock.passgarble.js

import community.flock.passwordgarble.components.passwordGenerator
import community.flock.passwordgarble.components.passwordValidator
import csstype.Size
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.Overflow
import kotlinx.css.backgroundColor
import kotlinx.css.display
import kotlinx.css.overflow
import kotlinx.css.padding
import react.Props
import react.dom.h1
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import styled.styledMain


val app = fc<Props> {

    // typesafe HTML goes here!
    h1("wave another") {
        +"Password generator / validator"
    }

    styledMain {
        css {
//            backgroundColor = Color.blue
//            overflow = Overflow.hidden

        }

        styledDiv {
            css {
//                    display = Display.flex
            }

            child(passwordGenerator){


                attrs {
                    something= "BLURP"
                }
            }
            child(passwordValidator)

        }
//        div{
//            (0..3).forEach {
//                p{
//                    +"Hello, I'm $it"
//                }
//            }
//        }
    }
}
