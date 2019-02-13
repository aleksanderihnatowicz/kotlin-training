package pl.allegro.training.kotlin.marketplace.adapter.rest.offer

import pl.allegro.training.kotlin.marketplace.adapter.rest.offer.DeliveryType.COURIER_SERVICE
import pl.allegro.training.kotlin.marketplace.adapter.rest.offer.DeliveryType.SELF_PICKUP
import pl.allegro.training.kotlin.marketplace.domain.offer.CourierService
import pl.allegro.training.kotlin.marketplace.domain.offer.Delivery
import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.SelfPickup
import java.lang.RuntimeException
import java.math.BigDecimal

class OfferCreationRequest(
    val title: String,
    val description: String,
    val price: BigDecimal,
    val deliveries: List<DeliveryCreationRequest>
) {
    fun asOffer(sellerId: String): Offer = Offer(
        sellerId = sellerId,
        title = title,
        description = description,
        price = price,
        deliveries = deliveries.map { it.asDelivery() }
    )
}

class DeliveryCreationRequest(
    val type: DeliveryType,
    val cost: BigDecimal?,
    val timeInDays: Int?
) {
    fun asDelivery(): Delivery = when (type) {
        SELF_PICKUP     -> SelfPickup
        COURIER_SERVICE -> {
            requireNotNull(cost) { throw InvalidDeliveryDataException("cost is undefined") }
            requireNotNull(timeInDays) { throw InvalidDeliveryDataException("timeInDays is undefined") }

            CourierService(cost, timeInDays)
        }
    }
}

class InvalidDeliveryDataException(message: String) : RuntimeException(message)
