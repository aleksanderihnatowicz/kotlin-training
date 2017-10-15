package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.domain.order.MemoryRepository

class MemoryAccountRepository : MemoryRepository<Account, String>(), AccountRepository