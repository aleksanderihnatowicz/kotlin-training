package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.logger

class AccountService(
    private val repository: AccountRepository,
    private val idGenerator: IdGenerator
) {
    fun addAccount(account: Account): Account {
        val id = if(account.id != null) {
            account.id
        } else {
            idGenerator.getNextId()
        }
        val accountWithId = account.copy(id = id)
        logger.debug("Adding account with id = {}", accountWithId)
        repository.save(accountWithId)
        return accountWithId
    }

    fun getAccountsByStatus(status: AccountStatus?): List<Account> =
        repository.findAll()
            .filter { status == it.status || status == null }

    companion object {
        private val logger by logger()
    }
}
