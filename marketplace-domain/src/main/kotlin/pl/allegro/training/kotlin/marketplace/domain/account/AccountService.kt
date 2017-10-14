package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.domain.misc.UuidIdGenerator

class AccountService {
    val repository = MemoryAccountRepository()
    val idGenerator = UuidIdGenerator()

    fun addAccount(account: Account): Account {
        val accountWithId = account.copy(id = account.id ?: idGenerator.getNextId())
        repository.save(accountWithId)
        return accountWithId
    }

    fun getAccounts(): List<Account> = repository.findAll()
}