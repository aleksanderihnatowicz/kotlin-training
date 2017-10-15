package pl.allegro.training.kotlin.marketplace.domain.offer

class InvalidOfferException : RuntimeException()

class OfferNotFoundException(offerId: String) : RuntimeException("Offer (id = $offerId) not found.")