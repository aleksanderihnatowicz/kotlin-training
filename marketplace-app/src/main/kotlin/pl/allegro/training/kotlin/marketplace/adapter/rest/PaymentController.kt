package pl.allegro.training.kotlin.marketplace.adapter.rest

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class PaymentController {

    @PostMapping("/orders/{orderId}/payments")
    fun createPayment(@PathVariable orderId: String, @RequestBody creationRequest: PaymentCreationRequest): PaymentResponse {
        return PaymentResponse()
    }

}

class PaymentCreationRequest

class PaymentResponse