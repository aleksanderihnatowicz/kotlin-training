package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.logger

class AccountService(
    private val accountRepository: AccountRepository,
    private val idGenerator: IdGenerator
) {
    fun addAccount(account: Account): Account {
        val accountWithId = account.copy(id = account.id ?: idGenerator.getNextId())
        logger.debug("Adding account with id = {}", accountWithId)
        accountRepository.save(accountWithId)
        return accountWithId
    }

    fun getAccountsByStatus(status: AccountStatus?): List<Account> =
        accountRepository.findAll()
            .filter { status == it.status || status == null }

    companion object {
        private val logger by logger()
    }
}
