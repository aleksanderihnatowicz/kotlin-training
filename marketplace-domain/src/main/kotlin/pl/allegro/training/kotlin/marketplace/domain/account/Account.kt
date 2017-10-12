package pl.allegro.training.kotlin.marketplace.domain.account

import EMAIL_REGEX

typealias Address = String

data class Account(
        val id: String?,
        val login: String,
        val password: String,
        val email: String,
        val phoneNumber: String? = null,
        val addresses: List<Address>
) {
    init {
        // isNullOrEmpty to extension function
        if(!email.isValidEmail()) {
            // tworzenie obiektu nie wymaga new
            throw InvalidAccountException("Invalid email: $email")
        }
    }

    private fun String.isValidEmail() = this matches EMAIL_REGEX

    fun addAddress(address: Address): Account = TODO()
}

// cwiczenia
// zaimplementowac Address
// getById
// dodać walidacje, nie można stworzyc account z pustą listą adresów