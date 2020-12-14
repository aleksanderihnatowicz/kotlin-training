package pl.allegro.training.kotlin.marketplace.domain.account

class Account(
    val id: String,
    val login: String,
    val passwordHash: String,
    val email: String,
    val status: AccountStatus = AccountStatus.ACTIVE,
    val phoneNumber: String? = null,
    val version: Int = 0
)

enum class AccountStatus {
    ACTIVE, BLOCKED
}

val HOST = "allegro.pl"

// String[]    Array<String>
// Integer[]   Array<Int>
// int[]       IntArray, BooleanArray, FloatArray

fun main(args: Array<String>) {
    val account = Account(
        status = AccountStatus.BLOCKED,
        email = "john@gmail.com",
        passwordHash = "CAFE",
        id = "1",
        login = "john",
        version = 1
    )

    println(account.login)
}
