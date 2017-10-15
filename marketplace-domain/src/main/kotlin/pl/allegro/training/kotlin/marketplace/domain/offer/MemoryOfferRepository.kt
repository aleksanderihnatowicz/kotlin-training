package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.domain.order.MemoryRepository

class MemoryOfferRepository : MemoryRepository<Offer, String>(), OfferRepository