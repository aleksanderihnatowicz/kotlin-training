package pl.allegro.training.kotlin.marketplace.domain.order

import pl.allegro.training.kotlin.marketplace.domain.account.AccountNotFoundException
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferNotFoundException
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository

class OrderValidator(private val offerRepository: OfferRepository, private val accountRepository: AccountRepository) {
    fun validate(order: Order) {
        if (offerRepository.findById(order.offerId) == null) {
            throw OfferNotFoundException(order.offerId)
        }

        if (accountRepository.findById(order.buyerId) == null) {
            throw AccountNotFoundException(order.buyerId)
        }
    }
}