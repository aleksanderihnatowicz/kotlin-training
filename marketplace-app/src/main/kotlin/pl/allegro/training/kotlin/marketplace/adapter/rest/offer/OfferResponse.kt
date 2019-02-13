package pl.allegro.training.kotlin.marketplace.adapter.rest.offer

import pl.allegro.training.kotlin.marketplace.adapter.rest.offer.DeliveryType.COURIER_SERVICE
import pl.allegro.training.kotlin.marketplace.adapter.rest.offer.DeliveryType.SELF_PICKUP
import pl.allegro.training.kotlin.marketplace.domain.offer.CourierService
import pl.allegro.training.kotlin.marketplace.domain.offer.Delivery
import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.SelfPickup
import java.math.BigDecimal

class OfferListResponse(val offers: List<OfferResponse>)

class OfferResponse(
    val id: String,
    val sellerId: String,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val deliveries: List<DeliveryResponse>
)

fun Offer.asOfferResponse() = OfferResponse(
    id = id!!,
    sellerId = sellerId,
    title = title,
    description = description,
    price = price,
    deliveries = deliveries.map { it.asDeliveryResponse() }
)

class DeliveryResponse(
    val type: DeliveryType,
    val cost: BigDecimal,
    val timeInDays: Int? = null
)

private fun Delivery.asDeliveryResponse(): DeliveryResponse = when (this) {
    is SelfPickup     -> DeliveryResponse(SELF_PICKUP, cost)
    is CourierService -> DeliveryResponse(COURIER_SERVICE, cost, timeInDays)
}
