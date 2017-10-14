package pl.allegro.training.kotlin.marketplace.domain.account

class MemoryAccountRepository : AccountRepository {
    private val accounts: MutableMap<String, Account> = mutableMapOf()

    override fun save(entity: Account) {
        if(entity.id == null) {
            throw InvalidAccountException("Id is not initialized.")
        }
        accounts.put(entity.id, entity)
    }

    override fun findAll(): List<Account> = accounts.values.toList()

    override fun findById(id: String): Account? = accounts[id]
}