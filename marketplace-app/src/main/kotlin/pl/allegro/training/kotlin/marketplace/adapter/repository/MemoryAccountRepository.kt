package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository

class MemoryAccountRepository : MemoryRepository<Account, String>(), AccountRepository