package pl.allegro.training.kotlin.marketplace.domain.account

interface AccountRepository {
    fun save(account: Account)
    fun getById(id: String): Account
}