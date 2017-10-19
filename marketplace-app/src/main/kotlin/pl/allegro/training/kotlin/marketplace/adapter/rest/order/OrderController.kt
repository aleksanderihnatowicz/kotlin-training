package pl.allegro.training.kotlin.marketplace.adapter.rest.order

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.order.OrderService

@RestController
class OrderController(private val orderService: OrderService) {

    @PostMapping("/accounts/{accountId}/orders")
    fun createOrder(@PathVariable accountId: String, @RequestBody creationRequest: OrderCreationRequest): ResponseEntity<OrderResponse> =
            orderService
                    .createOrder(creationRequest.asOrder(accountId))
                    .asOrderResponse()
                    .let { ResponseEntity(it, HttpStatus.CREATED) }

    @GetMapping("/accounts/{accountId}/orders")
    fun getOrders(@PathVariable accountId: String): OrderListResponse =
            orderService.getOrdersByBuyer(accountId).map { it.asOrderResponse() }.let { OrderListResponse(it) }

    @PostMapping("/orders/{orderId}/payment")
    fun addPayment(@PathVariable orderId: String, @RequestBody creationRequest: PaymentCreationRequest): OrderResponse {
        return orderService.addPayment(orderId, creationRequest.asPayment()).asOrderResponse()
    }
}

class InvalidPaymentDataException(message: String) : RuntimeException(message)