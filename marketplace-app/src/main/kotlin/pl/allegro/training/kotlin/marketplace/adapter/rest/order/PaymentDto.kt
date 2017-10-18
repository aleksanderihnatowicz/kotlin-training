package pl.allegro.training.kotlin.marketplace.adapter.rest.order

import com.fasterxml.jackson.annotation.JsonInclude
import pl.allegro.training.kotlin.marketplace.domain.order.Payment
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JsonInclude(JsonInclude.Include.NON_NULL)
class PaymentResponse(
        val paymentType: PaymentType,
        val cardNumber: String? = null,
        val expirationDate: String? = null,
        val cvv: String? = null,
        val walletAddress: String? = null
)

val expirationDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM")

// extension function on nullable type
// usage of run
fun Payment?.asPaymentResponse(): PaymentResponse? = this?.run {
    // when this is
    // no need for else
    // is on CashOnDeliver can be omitted, identity equality
    when (this) {
        is Payment.CashOnDelivery -> PaymentResponse(PaymentType.CASH_ON_DELIVERY)
        is Payment.CreditCard     -> PaymentResponse(PaymentType.CREDIT_CARD, cardNumber, expirationDate.format(expirationDateFormatter), cvv)
        is Payment.Bitcoin        -> PaymentResponse(PaymentType.BITCOIN, walletAddress)
    }
}

class PaymentCreationRequest(
        val paymentType: PaymentType,
        val cardNumber: String?,
        val expirationDate: String?,
        val cvv: String?,
        val walletAddress: String?
) {
    fun asPayment(): Payment {
        return when(paymentType) {
            PaymentType.CASH_ON_DELIVERY -> asCashOnDelivery()
            PaymentType.CREDIT_CARD      -> asCreditCard()
            PaymentType.BITCOIN          -> asBitcoin()
        }
    }

    private fun asCashOnDelivery(): Payment.CashOnDelivery = Payment.CashOnDelivery

    private fun asCreditCard(): Payment.CreditCard {
        if(cardNumber == null || expirationDate == null || cvv == null) {
            throw InvalidPaymentDataException("To create credit card payment provide card number, expiration date and cvv.")
        }
        // smart casts
        return Payment.CreditCard(cardNumber, YearMonth.parse(expirationDate, expirationDateFormatter), cvv)
    }

    private fun asBitcoin(): Payment.Bitcoin {
        if(walletAddress == null) {
            throw InvalidPaymentDataException("To create Bitcoin payment provide wallet address.")
        }
        return Payment.Bitcoin(walletAddress)
    }
}

enum class PaymentType {
    CASH_ON_DELIVERY, CREDIT_CARD, BITCOIN
}