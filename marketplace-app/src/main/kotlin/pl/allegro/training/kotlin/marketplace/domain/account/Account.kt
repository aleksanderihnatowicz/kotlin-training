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

val SPEED = 100

// String[]           Array<String>
// Integer[]          Array<Int>
// int[]              IntArray, FloatArray, BooleanArray

fun main(args: Array<String>) {
    val account = Account(
        id = "1",
        login = "john",
        passwordHash = "CAFE",
        email = "john@gmail.com",
        status = AccountStatus.BLOCKED,
        version = 1
    )
    println(account.login)
}
