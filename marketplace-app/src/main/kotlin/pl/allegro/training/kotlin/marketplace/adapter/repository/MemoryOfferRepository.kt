package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository
import pl.allegro.training.kotlin.marketplace.domain.order.MemoryRepository

class MemoryOfferRepository : MemoryRepository<Offer, String>(), OfferRepository