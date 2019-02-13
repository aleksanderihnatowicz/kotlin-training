package pl.allegro.training.kotlin.marketplace.adapter.rest.account

import pl.allegro.training.kotlin.marketplace.domain.account.Account

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
