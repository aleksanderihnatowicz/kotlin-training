package pl.allegro.training.kotlin.marketplace.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import java.math.BigDecimal


@RestController
class OfferController {

    // this is how to inject dependency to property
    @Autowired
    private lateinit var offerService: OfferService

    @PostMapping("/accounts/{accountId}/offers")
    fun addOffer(@PathVariable accountId: String, @RequestBody creationRequest: OfferCreationRequest): ResponseEntity<OfferResponse> {
        return offerService.addOffer(creationRequest.asOffer(accountId)).let { ResponseEntity(it.asOfferResponse(), HttpStatus.CREATED) }
    }

    // have to set default argument value via annotation, Kotlin's default arguments don't work with Spring 4 (5.0 changes that)
    @GetMapping("/offers")
    fun findOffers(@RequestParam(defaultValue = "aaa") query: String): OfferListResponse =
            offerService.findOffers(query)
                    .map { it.asOfferResponse() }
                    .let { OfferListResponse(it) }
}

class OfferCreationRequest(
        val title: String,
        val description: String,
        val price: BigDecimal
) {
    fun asOffer(sellerId: String): Offer = Offer(
            sellerId = sellerId,
            title = title,
            description = description,
            price = price
    )
}

class OfferResponse(
        val id: String,
        val sellerId: String,
        val title: String,
        val description: String,
        val price: BigDecimal
)

private fun Offer.asOfferResponse() = OfferResponse(
        id = id!!,
        sellerId = sellerId,
        title = title,
        description = description,
        price = price
)

class OfferListResponse(val offers: List<OfferResponse>)

