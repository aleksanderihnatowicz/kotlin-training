import pl.allegro.training.kotlin.marketplace.domain.InvalidAccount
import java.util.*

typealias Address = String

private val VALID_EMAIL_REGEX = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

data class Account(
        val id: String?,
        val login: String,
        val password: String,
        val email: String,
        val phoneNumber: String? = null,
        val addresses: List<Address>
) {
    init {
        // isNullOrEmpty to extension function
        if(!email.isValidEmail()) {
            // tworzenie obiektu nie wymaga new
            throw InvalidAccount("Invalid email: $email")
        }
    }

    private fun String.isValidEmail() = this matches VALID_EMAIL_REGEX

    fun addAddress(address: Address): Account = TODO()
}

class AccountService {
    val repository = MemoryAccountRepository()
    val idGenerator = UuidIdGenerator()

    fun addAccount(account: Account): Account {
        val accountWithId = account.copy(id = account.id ?: idGenerator.getNextId())
        repository.save(accountWithId)
        return accountWithId
    }
}

interface AccountRepository {
    fun save(account: Account)
    fun getById(id: String): Account
}

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

class InvalidAccountException(message: String) : RuntimeException(message)

interface IdGenerator {
    fun getNextId(): String
}

class UuidIdGenerator : IdGenerator {
    override fun getNextId(): String = UUID.randomUUID().toString()
}

// cwiczenia
// zaimplementowac Address
// getById
// dodać walidacje, nie można stworzyc account z pustą listą adresów