package pl.allegro.training.kotlin.marketplace.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import pl.allegro.training.kotlin.util.hash.SecureHashAlgorithmUtils as SHA // alias - renaming class to shorter name

// annotation on constructor - no need to do this in current versions of Spring
//@RestController
//class AccountController2 @Autowired constructor(private val accountService: AccountService) {
//
//}

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping
    fun addAccount(@RequestBody creationRequest: AccountCreationRequest): ResponseEntity<AccountResponse> =
            accountService.addAccount(creationRequest.asAccount())
                    .let { ResponseEntity(it.asAccountResponse(), HttpStatus.CREATED) }

    @GetMapping
    fun getAccounts(): AccountListResponse = accountService.getAccounts()
            .map { it.asAccountResponse() }
            .let { AccountListResponse(it) }
}

class AccountCreationRequest(
        val login: String,
        val password: String,
        val email: String,
        val phoneNumber: String?
) {
    fun asAccount(): Account = Account(
            login = login,
            // cannot use sha256 if marked as internal
            passwordHash = SHA.sha256(password),
            email = email,
            phoneNumber = phoneNumber,
            addresses = emptyList()
    )
}

class AccountResponse(
        val id: String,
        val login: String,
        val email: String,
        val phoneNumber: String?,
        val version: Long
)

fun Account.asAccountResponse() = AccountResponse(
        id = id!!,
        login = login,
        email = email,
        phoneNumber = phoneNumber,
        // no implicit lossless conversion
        version = version.toLong()
)

class AccountListResponse(
        val accounts: List<AccountResponse>
)
