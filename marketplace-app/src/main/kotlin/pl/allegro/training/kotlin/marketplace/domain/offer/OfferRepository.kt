package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Repository

// interface inheritance
interface OfferRepository : Repository<Offer, String>