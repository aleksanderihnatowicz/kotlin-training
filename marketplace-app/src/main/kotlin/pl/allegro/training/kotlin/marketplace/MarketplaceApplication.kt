package pl.allegro.training.kotlin.marketplace

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService

@SpringBootApplication
class MarketplaceApplication {
    @Bean
    fun accountService() = AccountService()
}

fun main(args: Array<String>) {
    // tell about Array, arrayOf() and spread operator
    SpringApplication.run(MarketplaceApplication::class.java, *args)
}
