package pl.allegro.training.kotlin.marketplace.domain.account

class MemoryAccountRepository : AccountRepository {
    private val accounts: MutableMap<String, Account> = mutableMapOf()

    override fun save(account: Account) {
        if(account.id == null) {
            throw InvalidAccountException("Id is not initialized.")
        }
        accounts.put(account.id, account)
    }

    override fun getById(id: String): Account = TODO("""
        Treść zadania
    """)
}