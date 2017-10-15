package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.domain.misc.Identifiable
import pl.allegro.training.kotlin.marketplace.domain.misc.isValidEmail

class Rating(val likes: Int, val dislikes: Int) {
    // secondary constructor - must call primary
    constructor(data: Pair<Int, Int>) : this(data.first, data.second)

    companion object {
        val INITIAL = Rating(0, 0)
    }

    // virtual property - custom getter
    val likeRatio: Double
        get() = likes.toDouble() / (likes + dislikes).toDouble()

    // deconstruction contract
    operator fun component1() = likes

    operator fun component2() = dislikes
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