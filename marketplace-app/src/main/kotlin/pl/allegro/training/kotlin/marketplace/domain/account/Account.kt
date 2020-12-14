package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.isValidEmail

class Account(
    val id: String,
    val login: String,
    val passwordHash: String,
    val email: String,
    val status: AccountStatus = AccountStatus.ACTIVE,
    val phoneNumber: String? = null,
    val version: Int = 0
) {
    init {
        if(!isValidEmail(email)) {
            throw InvalidAccountException("invalid email: $email")
        }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

fun main(args: Array<String>) {
    val account = try {
        Account(
            email = "gmail.com",
            passwordHash = "CAFE",
            id = "1",
            login = "john"
        )
    } catch(e: InvalidAccountException) {
        println("Exception: $e")
        null
    }

    println(account!!.login)
}
