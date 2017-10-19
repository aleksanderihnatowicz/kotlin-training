package pl.allegro.training.kotlin.marketplace.adapter.rest

import spock.lang.Specification


class ExceptionHandlingAdviceSpec extends Specification {

    class MyException extends RuntimeException {
        MyException(String message) {
            super(message)
        }
    }

    def "should convert exception into error message"() {
        given:
        def exception = new MyException("An error message")

        expect:
        // invoking extension method - receiver as first argument
        ExceptionHandlingAdviceKt.asErrorResponse(exception) == new ErrorResponse("MyException", "An error message")
    }

}
