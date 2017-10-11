package pl.allegro.training.kotlin.marketplace.domain

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
// alias
import java.time.ZonedDateTime as DateTime

// powiedziec o typealias
typealias AccountId = Int

data class Offer(
        val id: String,
        val price: BigDecimal,
        val discount: BigDecimal?,
        val title: String,
        val description: String,
        val createdOn: DateTime,
        val status: OfferStatus,
        val accountId: AccountId

) {
    // definicja property
    // nadpisywanie gettera
    // elvis
    // operacje na BigDecimal
    val discountedPrice: BigDecimal
        get() = price * (BigDecimal.ONE - (discount ?: BigDecimal.ZERO))

    // when jako expression, if też
    // copy
    fun deactivate(): Offer {
        return when (status) {
            OfferStatus.ACTIVE -> copy(status = OfferStatus.INACTIVE)
            else -> throw OfferAlreadyInactiveException()
        }
    }
}

class OfferAlreadyInactiveException : RuntimeException()

// definicja enuma
enum class OfferStatus {
    ACTIVE, INACTIVE
}

//dodać walidacje, nie mozna utworzyc obiekt z price < 0; discount w zakresie 0 < d < 1
//dodac funkcje reaktywacji oferty
