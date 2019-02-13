package pl.allegro.training.kotlin.marketplace.adapter.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pl.allegro.training.kotlin.marketplace.adapter.rest.offer.InvalidDeliveryDataException
import pl.allegro.training.kotlin.marketplace.domain.account.AccountNotFoundException


@ControllerAdvice
class ExceptionHandlingAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [
        AccountNotFoundException::class,
        InvalidDeliveryDataException::class
    ])
    protected fun handleNotFound(exception: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> =
        ResponseEntity(exception.asErrorResponse(), HttpStatus.NOT_FOUND)

}

data class ErrorResponse(val exception: String?, val message: String?)

fun RuntimeException.asErrorResponse() = ErrorResponse(this::class.simpleName, this.message)