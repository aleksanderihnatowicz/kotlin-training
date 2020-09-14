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
            throw InvalidAccountException("msg")
        }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

fun main(args: Array<String>) {
    var account: Account? = null
    try {
        account = Account(
            id = "1",
            login = "john",
            passwordHash = "CAFE",
            email = "gmail.com",
            status = AccountStatus.BLOCKED,
            version = 1
        )
    } catch (e: InvalidAccountException) {
        println("cannot create account")
    }
    println(account!!.login)
}
