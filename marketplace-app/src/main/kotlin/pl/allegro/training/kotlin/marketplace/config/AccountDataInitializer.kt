package pl.allegro.training.kotlin.marketplace.config

import org.springframework.stereotype.Component
import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.marketplace.domain.account.AccountService
import javax.annotation.PostConstruct

@Component
class AccountDataInitializer(private val service: AccountService) {

    @PostConstruct
    fun init() {
        val sampleAccount = Account(
                id = "F4375026",
                login = "john",
                passwordHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                email = "john@gmail.com",
                phoneNumber = "+48 123 456 789",
                addresses = emptyList(),
                version = 0
        )
        service.addAccount(sampleAccount)
    }

}