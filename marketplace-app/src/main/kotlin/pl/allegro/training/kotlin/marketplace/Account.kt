package pl.allegro.training.kotlin.marketplace

import pl.allegro.training.kotlin.marketplace.AccountStatus.ACTIVE
import pl.allegro.training.kotlin.marketplace.AccountStatus.BLOCKED
import pl.allegro.training.kotlin.marketplace.infrastructure.isValidEmail

class Account(
    val id: String,
    val login: String,
    val passwordHash: String,
    val email: String,
    val status: AccountStatus = ACTIVE,
    val phoneNumber: String? = null,
    val version: Int = 0 // Float, Boolean, Double, Short
) {
    init {
      if(!isValidEmail(email)) {
          throw InvalidAccountException(email)
      }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

val SPEED = 5

// String[]  ->  Array<String>
// Integer[] ->  Array<Int>
// int[]     ->  IntArray

fun main(args: Array<String>) {
    val account = try {
        Account(
            id = "1",
            email = "john",
            passwordHash = "CAFE",
            status = BLOCKED,
            login = "john",
            version = 1
        )
    } catch(e: InvalidAccountException) {
        null
    }

    println(account!!.login)
}
