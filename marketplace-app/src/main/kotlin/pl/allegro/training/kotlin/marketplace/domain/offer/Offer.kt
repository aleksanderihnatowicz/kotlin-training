package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable
import java.math.BigDecimal

data class Offer(
    override val id: String? = null,
    val sellerId: String,
    val title: String,
    val description: String,
    val price: BigDecimal = BigDecimal.ZERO,
    val deliveries: List<Delivery> = emptyList()
) : Identifiable<String>

