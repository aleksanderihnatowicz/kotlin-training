package pl.allegro.training.kotlin.marketplace

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MarketplaceApplication

fun main(args: Array<String>) {
    SpringApplication.run(MarketplaceApplication::class.java, *args)
}
