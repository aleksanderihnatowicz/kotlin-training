package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.isValidEmail
import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable

data class Account(
        override val id: String? = null,
        val login: String,
        val passwordHash: String,
        val email: String,
        val phoneNumber: String? = null,
        val addresses: List<Address>,
        val version: Int = 0,
        val rating: Rating = Rating.INITIAL
) : Identifiable<String> {

    init {
        if(!isValidEmail(email)) {
            // no new operator upon object construction
            throw InvalidAccountException("Invalid email: $email")
        }
    }

    fun addAddress(address: Address): Account = TODO()
}

open class Address(
        val street: String,
        val city: String,
        val zipCode: String
)

// cwiczenia
// zaimplementowac Address
// getById
// dodać walidacje, nie można stworzyc account z pustą listą adresów