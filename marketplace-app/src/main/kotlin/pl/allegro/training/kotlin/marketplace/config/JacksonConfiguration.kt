package pl.allegro.training.kotlin.marketplace.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal


@Configuration
class JacksonConfiguration {
    @Bean
    fun moneyModule(): Module = SimpleModule().addSerializer(BigDecimalSerializer())
}

// https://stackoverflow.com/questions/11319445/java-to-jackson-json-serialization-money-fields
class BigDecimalSerializer : JsonSerializer<BigDecimal>() {

    override fun serialize(value: BigDecimal?, generator: JsonGenerator?, serializers: SerializerProvider?) {
        val stringValue = value?.setScale(2, BigDecimal.ROUND_HALF_UP).toString()
        generator?.writeString(stringValue)
    }

    override fun handledType(): Class<BigDecimal> = BigDecimal::class.java
}