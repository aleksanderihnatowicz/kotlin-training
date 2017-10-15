package pl.allegro.training.kotlin.marketplace.adapter.repository

import pl.allegro.training.kotlin.marketplace.domain.account.Account
import pl.allegro.training.kotlin.marketplace.domain.account.AccountRepository
import pl.allegro.training.kotlin.marketplace.domain.order.MemoryRepository

class MemoryAccountRepository : MemoryRepository<Account, String>(), AccountRepository