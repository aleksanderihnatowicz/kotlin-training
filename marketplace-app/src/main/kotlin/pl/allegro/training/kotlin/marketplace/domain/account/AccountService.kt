package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.logger

// 4a. Pokazac serwis z logerem stworzonym bez delegatow, omowienie interfejsow, dziedziczenie interfejsów
// 4b. Omówienie implementacji IdGeneratora, if jako expression, przepisac na when a potem elvis,
// nie ma ? : z javy
// wyjasniamy w calosci jak dzialaja companion objecty, skladnia object {}

class AccountService(
        private val accountRepository: AccountRepository,
        private val idGenerator: IdGenerator
) {
    fun addAccount(account: Account): Account {
        // elvis operator najpierw napisac jako if
        val accountWithId = account.copy(id = account.id ?: idGenerator.getNextId())
        logger.debug("Adding account with id = {}", accountWithId)
        accountRepository.save(accountWithId)
        return accountWithId
    }

    fun getAccounts(): List<Account> = accountRepository.findAll()

    companion object {
        private val logger by logger()
    }
}