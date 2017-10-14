package pl.allegro.training.kotlin.marketplace.domain.order

import java.math.BigDecimal

class Basket

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

class Order(
        val offerId: String,
        val buyerId: String

)
