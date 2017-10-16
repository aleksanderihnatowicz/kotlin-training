package pl.allegro.training.kotlin.marketplace.config

import org.springframework.stereotype.Component
import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Component
class OfferDataInitializer(private val service: OfferService) {

    @PostConstruct
    fun init() {
        val sampleOffer = Offer(
                id = "5D149384",
                sellerId = "F4375026",
                title = "Apple MacBook Pro Retina 13 inch",
                description = """
                    It’s razor thin, feather light, and even faster and more powerful than before.
                    It has the brightest, most colorful Mac notebook display ever.
                """.trimIndent(),
                price = BigDecimal(140.99)
        )
        service.addOffer(sampleOffer)
    }

}
