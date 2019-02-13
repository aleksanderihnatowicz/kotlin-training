package pl.allegro.training.kotlin.marketplace.adapter.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.adapter.rest.DeliveryType.COURIER_SERVICE
import pl.allegro.training.kotlin.marketplace.adapter.rest.DeliveryType.SELF_PICKUP
import pl.allegro.training.kotlin.marketplace.domain.offer.CourierService
import pl.allegro.training.kotlin.marketplace.domain.offer.Delivery
import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import pl.allegro.training.kotlin.marketplace.domain.offer.SelfPickup
import java.lang.RuntimeException
import java.math.BigDecimal


@RestController
class OfferController {

    @Autowired
    private lateinit var offerService: OfferService

    @PostMapping("/accounts/{accountId}/offers")
    fun addOffer(@PathVariable accountId: String, @RequestBody creationRequest: OfferCreationRequest): ResponseEntity<OfferResponse> {
        return offerService.addOffer(creationRequest.asOffer(accountId)).let { ResponseEntity(it.asOfferResponse(), HttpStatus.CREATED) }
    }

    @GetMapping("/offers")
    fun findOffers(@RequestParam query: String = ""): OfferListResponse =
        offerService.findOffers(query)
            .map { it.asOfferResponse() }
            .let { OfferListResponse(it) }
}

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

enum class DeliveryType {
    SELF_PICKUP, COURIER_SERVICE
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

class OfferResponse(
    val id: String,
    val sellerId: String,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val deliveries: List<DeliveryResponse>
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

private fun Offer.asOfferResponse() = OfferResponse(
    id = id!!,
    sellerId = sellerId,
    title = title,
    description = description,
    price = price,
    deliveries = deliveries.map { it.asDeliveryResponse() }
)

class OfferListResponse(val offers: List<OfferResponse>)
