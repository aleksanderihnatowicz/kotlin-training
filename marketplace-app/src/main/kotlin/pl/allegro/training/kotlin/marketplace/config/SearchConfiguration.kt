package pl.allegro.training.kotlin.marketplace.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "search")
data class SearchConfiguration(
    val allowEmptyQuery: Boolean
)
