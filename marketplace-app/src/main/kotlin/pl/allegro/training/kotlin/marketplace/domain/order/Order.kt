package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable

data class Order(
        override val id: String? = null,
        val offerId: String,
        val buyerId: String,
        val status: OrderStatus = OrderStatus.NOT_PAID,
        val payment: Payment? = null
) : Identifiable<String>

enum class OrderStatus {
    NOT_PAID, PENDING, ACCEPTED, DELIVERED
}
