package pl.allegro.training.kotlin.marketplace.domain.account

class InvalidAccountException(message: String) : RuntimeException(message)

class AccountNotFoundException(accountId: String) : RuntimeException("Account (id = $accountId) not found.")