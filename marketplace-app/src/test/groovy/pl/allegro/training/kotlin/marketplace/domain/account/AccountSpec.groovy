package pl.allegro.training.kotlin.marketplace.domain.account

import spock.lang.Specification

class AccountSpec extends Specification {
    def "should throw on creation when email is invalid"() {
        given:
        def invalidEmail = "gmail.com"

        when:
        new Account("1", "john.doe", "A4B23FE1", invalidEmail, null, AccountStatus.ACTIVE, 0, Rating.INITIAL)

        then:
        thrown InvalidAccountException
    }
}
