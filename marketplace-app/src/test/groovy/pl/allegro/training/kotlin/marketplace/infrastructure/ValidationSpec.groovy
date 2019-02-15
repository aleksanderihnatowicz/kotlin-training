package pl.allegro.training.kotlin.marketplace.infrastructure

import spock.lang.Specification

class ValidationSpec extends Specification {
    def "correct email should pass validation"() {
        given:
        def email = "john@doe.com"

        expect:
        ValidationKt.isValidEmail(email) == true
    }

    def "wrong email should fail validation"() {
        given:
        def email = "email@-example.com"

        expect:
        ValidationKt.isValidEmail(email) == false
    }

}
