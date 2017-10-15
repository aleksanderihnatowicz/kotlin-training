package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.domain.offer.Offer
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository

class MemoryOfferRepository : MemoryRepository<Offer, String>(), OfferRepository