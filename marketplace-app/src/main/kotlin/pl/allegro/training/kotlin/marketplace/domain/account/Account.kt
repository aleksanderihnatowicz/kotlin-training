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
    val status: AccountStatus = AccountStatus.ACTIVE,
    val version: Int = 0,
    val rating: Rating = Rating.INITIAL
) : Identifiable<String> {

    init {
        if (!isValidEmail(email)) {
            throw InvalidAccountException("Invalid email: $email")
        }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

class Address(
    val street: String,
    val city: String,
    val zipCode: String
)


