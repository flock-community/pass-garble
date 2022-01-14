package community.flock.passwordgarble.components

import community.flock.passgarble.common.PasswordGenerator
import community.flock.passgarble.common.PasswordGeneratorFactory
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.margin
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import react.Props
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.h3
import react.dom.li
import react.dom.pre
import react.dom.ul
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import kotlin.js.Date


external interface PasswordGeneratorProps : Props {
    var something: String
}

val passwordGenerator = fc<PasswordGeneratorProps> { props ->
    var clicks: List<String> by useState(mutableListOf())
    val passwordGenerator: PasswordGenerator by useState(PasswordGeneratorFactory.createGenerator())

    // typesafe HTML goes here!
    div {
        h3 {
            +"Generate a password"
        }
        button {
            attrs {
                onClickFunction = {
                    clicks = clicks + "${Date().toISOString()} - ${passwordGenerator.generate()}"
                }
            }
            +"Generate password for ${props.something}"
        }
        styledDiv {
            css {
                "li" {
                    margin(10.px)
                    lastOfType {
                        backgroundColor = Color.aqua
                    }
                }
            }
            ul {
                clicks.forEachIndexed { index, click ->
                    li {
                        key = "click-$index"

                        pre {
                            +click
                        }
                    }
                }
            }
        }
    }
}
