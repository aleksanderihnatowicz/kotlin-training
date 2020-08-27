package pl.allegro.training.kotlin.marketplace.domain.account

import javax.xml.validation.Validator

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
            throw InvalidAccountException("cannot create account. invalid email: $email")
        }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

val speed = 42

// String[]       Array<String>
// Integer[]      Array<Int>
// int[]          IntArray, FloatArray, BooleanArray

fun main() {
    val account = try {
        Account(
            id = "1",
            passwordHash = "CAFE",
            login = "john",
            email = "gmail.com",
        )
    } catch(e: InvalidAccountException) {
        println(e.message)
        null
    }

    println(account!!.login)
}

