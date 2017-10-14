package pl.allegro.training.kotlin.marketplace.api

import org.springframework.web.bind.annotation.RestController


@RestController
class OfferController {

//    @Autowired
//    private lateinit var offerService: OfferService
//
//    @PostMapping("/accounts/{accountId}/offers")
//    fun addOffer(@PathVariable accountId: String): ResponseEntity<AccountCreationRequest> {
//
//    }
//
//    @GetMapping("/offers")
//    fun findOffers(@RequestParam query: String = ""): OfferListDto =
//            offerService.findOffers(query)
//                    .map { it.asOfferDto() }
//                    .let { OfferListDto(it) }
}

class OfferDto

//fun Offer.asOfferDto(): OfferDto = OfferDto()
//
//class OfferListDto(val offers: List<OfferDto>)