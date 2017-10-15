package pl.allegro.training.kotlin.marketplace.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.order.Order
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
}

class OrderCreationRequest(val offerId: String) {

    fun asOrder(buyerId: String): Order = Order(offerId = offerId, buyerId = buyerId)
}

private fun Order.asOrderResponse(): OrderResponse = OrderResponse(id!!, offerId, status.name)

class OrderResponse(
        val id: String,
        val offerId: String,
        val status: String
)

class OrderListResponse(val orders: List<OrderResponse>)