package pl.allegro.training.kotlin.marketplace.adapter.rest.account

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import pl.allegro.training.kotlin.marketplace.domain.account.AccountStatus
import pl.allegro.training.kotlin.util.hash.SecureHashAlgorithmUtils as SHA

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping
    fun addAccount(@RequestBody @Validated creationRequest: AccountCreationRequest): ResponseEntity<AccountResponse> {
        val account = accountService.addAccount(creationRequest.asAccount())
        return ResponseEntity(account.asAccountResponse(), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAccounts(@RequestParam status: AccountStatus?): AccountListResponse =
        accountService.getAccountsByStatus(status)
            .map { it.asAccountResponse() }
            .let { AccountListResponse(it) }
}
