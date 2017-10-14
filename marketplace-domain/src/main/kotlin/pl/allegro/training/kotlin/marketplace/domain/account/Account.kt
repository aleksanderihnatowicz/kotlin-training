package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.domain.offer.Identifiable

// compile-time constant - only primitive types + String
const val EMAIL_REGEX = "[a-zA-Z.]+@[a-zA-Z.]+"

class Rating(val likes: Int, val dislikes: Int) {
    // secondary constructor - must call primary
    constructor(data: Pair<Int, Int>) : this(data.first, data.second)

    companion object {
        val INITIAL = Rating(0, 0)
    }

    // virtual property - custom getter
    val likeRatio: Double
        get() = likes.toDouble() / (likes + dislikes).toDouble()
}

data class Account(
        override val id: String? = null,
        val login: String,
        val passwordHash: String,
        val email: String,
        val phoneNumber: String? = null,
        val addresses: List<Address>,
        val version: Int = 0,
        val rating: Rating = Rating.INITIAL
) : Identifiable {
    init {
        // isNullOrEmpty to extension function
        if(!email.isValidEmail()) {
            // tworzenie obiektu nie wymaga new
            throw InvalidAccountException("Invalid email: $email")
        }
    }

    // infix notation of matches
    private fun String.isValidEmail() = this matches Regex(EMAIL_REGEX)

    fun addAddress(address: Address): Account = TODO()
}

open class Address(
        val street: String,
        val city: String,
        val zipCode: String
) {
    constructor(data: Triple<String, String, String>) : this(data.first, data.second, data.third)
}

// cwiczenia
// zaimplementowac Address
// getById
// dodać walidacje, nie można stworzyc account z pustą listą adresów