package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable
import java.math.BigDecimal

sealed class DeliveryMethod {
    object InStorePickup
    class Courier(val cost: BigDecimal)
    class DroneShipment()
}

sealed class PaymentMethod {
    object CashOnDelivery
    class BankTransfer(val accountNumber: String)
    class Bitcoin(val walletAddress: String)
}

data class Order(
        override val id: String? = null,
        val offerId: String,
        val buyerId: String,
        val status: OrderStatus = OrderStatus.PENDING
) : Identifiable<String>

enum class OrderStatus {
    NOT_PAID, PENDING, ACCEPTED, DELIVERED
}

// dopisać endpoint
val c = OrderStatus.PENDING..OrderStatus.DELIVERED
