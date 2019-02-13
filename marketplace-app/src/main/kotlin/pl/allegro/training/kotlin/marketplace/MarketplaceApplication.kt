package pl.allegro.training.kotlin.marketplace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import pl.allegro.training.kotlin.marketplace.adapter.repository.MemoryAccountRepository
import pl.allegro.training.kotlin.marketplace.adapter.repository.MemoryOfferRepository
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import pl.allegro.training.kotlin.marketplace.infrastructure.id.HexIdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator

@SpringBootApplication
class MarketplaceApplication {
    @Bean
    fun accountRepository() = MemoryAccountRepository()

    @Bean
    fun offerRepository() = MemoryOfferRepository()

    @Bean
    fun accountService(accountRepository: AccountRepository, idGenerator: IdGenerator) = AccountService(accountRepository, idGenerator)

    @Bean
    fun offerService(offerRepository: OfferRepository, idGenerator: IdGenerator) = OfferService(offerRepository, idGenerator)

    @Bean
    fun idGenerator() = HexIdGenerator()
}

fun main(args: Array<String>) {
    runApplication<MarketplaceApplication>(*args)
}
