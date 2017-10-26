package pl.allegro.training.kotlin.marketplace.domain.order

import java.time.YearMonth

sealed class Payment {
    object CashOnDelivery : Payment()
    class CreditCard(val cardNumber: String, val expirationDate: YearMonth, val cvv: String): Payment()
    class Bitcoin(val walletAddress: String): Payment()
}