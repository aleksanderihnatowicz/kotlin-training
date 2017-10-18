package pl.allegro.training.kotlin.marketplace.adapter.rest.order

import com.fasterxml.jackson.annotation.JsonInclude
import pl.allegro.training.kotlin.marketplace.domain.order.Order
import pl.allegro.training.kotlin.marketplace.domain.order.OrderStatus


class OrderCreationRequest(val offerId: String) {
    fun asOrder(buyerId: String): Order = Order(offerId = offerId, buyerId = buyerId)
}

fun Order.asOrderResponse(): OrderResponse = OrderResponse(
        id = id!!,
        offerId = offerId,
        // status in range
        paid = (OrderStatus.PENDING..OrderStatus.DELIVERED).contains(status),
        payment = payment.asPaymentResponse()
)

@JsonInclude(JsonInclude.Include.NON_NULL)
class OrderResponse(
        val id: String,
        val offerId: String,
        val paid: Boolean,
        val payment: PaymentResponse?
)

class OrderListResponse(val orders: List<OrderResponse>)