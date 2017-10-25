package pl.allegro.training.kotlin.marketplace

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import pl.allegro.training.kotlin.marketplace.adapter.repository.MemoryAccountRepository
import pl.allegro.training.kotlin.marketplace.adapter.repository.MemoryOfferRepository
import pl.allegro.training.kotlin.marketplace.adapter.repository.MemoryOrderRepository
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import pl.allegro.training.kotlin.marketplace.domain.order.OrderRepository
import pl.allegro.training.kotlin.marketplace.domain.order.OrderService
import pl.allegro.training.kotlin.marketplace.domain.order.OrderValidator
import pl.allegro.training.kotlin.marketplace.infrastructure.id.HexIdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator

// 9b. konfig musi byc open, fun tez, opowiedziec o pluginie gradleowym, spread operator

@SpringBootApplication
class MarketplaceApplication {
    @Bean
    fun accountRepository() = MemoryAccountRepository()

    @Bean
    fun offerRepository() = MemoryOfferRepository()

    @Bean
    fun orderRepository() = MemoryOrderRepository()

    @Bean
    fun accountService(accountRepository: AccountRepository, idGenerator: IdGenerator) = AccountService(accountRepository, idGenerator)

    @Bean
    fun offerService(offerRepository: OfferRepository, idGenerator: IdGenerator) = OfferService(offerRepository, idGenerator)

    @Bean
    fun orderService(
            idGenerator: IdGenerator,
            orderRepository: OrderRepository,
            accountRepository: AccountRepository,
            orderValidator: OrderValidator
    ) = OrderService(idGenerator, orderRepository, accountRepository, orderValidator)

    @Bean
    fun idGenerator() = HexIdGenerator()

    @Bean
    fun orderValidator(offerRepository: OfferRepository, accountRepository: AccountRepository) = OrderValidator(offerRepository, accountRepository)
}

fun main(args: Array<String>) {
    // tell about Array, arrayOf() and spread operator
    SpringApplication.run(MarketplaceApplication::class.java, *args)
}


