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

val speed = 42

// String[]       Array<String>
// Integer[]      Array<Int>
// int[]          IntArray, FloatArray, BooleanArray

fun main(args: Array<String>) {
    val account = Account(
        passwordHash = "CAFE",
        login = "john",
        email = "john@gmail.com",
        status = AccountStatus.BLOCKED,
        id = "1",
        version = 1
    )

    account.id
}

