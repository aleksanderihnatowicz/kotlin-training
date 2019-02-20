package pl.allegro.training.kotlin.marketplace.adapter.rest.account

import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.util.hash.SecureHashAlgorithmUtils
import javax.validation.constraints.Size

class AccountCreationRequest(
    val login: String,
    @get:Size(min=8, max=256)
    val password: String,
    val email: String,
    val phoneNumber: String?
) {
    fun asAccount(): Account = Account(
        login = login,
        passwordHash = SecureHashAlgorithmUtils.sha256(password),
        email = email,
        phoneNumber = phoneNumber
    )
}
