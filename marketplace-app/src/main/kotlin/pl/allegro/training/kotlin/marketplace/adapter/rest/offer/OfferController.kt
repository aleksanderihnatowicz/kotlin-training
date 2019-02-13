package pl.allegro.training.kotlin.marketplace.adapter.rest.offer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService

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
