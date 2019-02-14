package pl.allegro.training.kotlin.marketplace.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "search")
class SearchConfiguration {
    var allowEmptyQuery: Boolean = false
}
