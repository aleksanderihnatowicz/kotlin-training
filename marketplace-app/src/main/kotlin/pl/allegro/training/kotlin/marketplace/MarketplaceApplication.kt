package pl.allegro.training.kotlin.marketplace

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import pl.allegro.training.kotlin.marketplace.domain.account.MemoryAccountRepository
import pl.allegro.training.kotlin.marketplace.domain.misc.IdGenerator
import pl.allegro.training.kotlin.marketplace.domain.misc.UuidIdGenerator
import pl.allegro.training.kotlin.marketplace.domain.offer.MemoryOfferRepository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferRepository
import pl.allegro.training.kotlin.marketplace.domain.offer.OfferService
import pl.allegro.training.kotlin.marketplace.domain.order.MemoryOrderRepository
import pl.allegro.training.kotlin.marketplace.domain.order.OrderRepository
import pl.allegro.training.kotlin.marketplace.domain.order.OrderService
import pl.allegro.training.kotlin.marketplace.domain.order.OrderValidator
import java.math.BigDecimal



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
    fun idGenerator() = UuidIdGenerator()

    @Bean
    fun orderValidator(offerRepository: OfferRepository, accountRepository: AccountRepository) = OrderValidator(offerRepository, accountRepository)

    // by default, Spring Boot serializes BigDecimal to json number
    @Bean
    fun moneyModule(): Module = SimpleModule().addSerializer(BigDecimalSerializer())
}

// actually, the code is from StackOverflow, pasted and a little bit refactored. The code was in Java, but Idea did the great job converting it to Kotlin.
// https://stackoverflow.com/questions/11319445/java-to-jackson-json-serialization-money-fields
class BigDecimalSerializer : JsonSerializer<BigDecimal>() {

    override fun serialize(value: BigDecimal?, generator: JsonGenerator?, serializers: SerializerProvider?) {
        val stringValue = value?.setScale(2, BigDecimal.ROUND_HALF_UP).toString()
        generator?.writeString(stringValue)
    }

    override fun handledType(): Class<BigDecimal> = BigDecimal::class.java
}

fun main(args: Array<String>) {
    // tell about Array, arrayOf() and spread operator
    SpringApplication.run(MarketplaceApplication::class.java, *args)
}


