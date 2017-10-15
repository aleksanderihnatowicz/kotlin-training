package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.domain.misc.IdGenerator

class AccountService(private val accountRepository: AccountRepository, private val idGenerator: IdGenerator) {

    fun addAccount(account: Account): Account {
        val accountWithId = account.copy(id = account.id ?: idGenerator.getNextId())
        accountRepository.save(accountWithId)
        return accountWithId
    }

    fun getAccounts(): List<Account> = accountRepository.findAll()
}