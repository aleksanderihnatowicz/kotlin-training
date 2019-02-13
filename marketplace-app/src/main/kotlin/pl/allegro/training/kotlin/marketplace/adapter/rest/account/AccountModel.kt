package pl.allegro.training.kotlin.marketplace.adapter.rest.account

import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.util.hash.SecureHashAlgorithmUtils

class AccountCreationRequest(
    val login: String,
    val password: String,
    val email: String,
    val phoneNumber: String?
) {
    fun asAccount(): Account = Account(
        login = login,
        passwordHash = SecureHashAlgorithmUtils.sha256(password),
        email = email,
        phoneNumber = phoneNumber,
        addresses = emptyList()
    )
}

class AccountListResponse(
    val accounts: List<AccountResponse>
)

class AccountResponse(
    val id: String,
    val login: String,
    val email: String,
    val phoneNumber: String?,
    val version: Long
)

fun Account.asAccountResponse() = AccountResponse(
    id = id!!,
    login = login,
    email = email,
    phoneNumber = phoneNumber,
    version = version.toLong()
)
