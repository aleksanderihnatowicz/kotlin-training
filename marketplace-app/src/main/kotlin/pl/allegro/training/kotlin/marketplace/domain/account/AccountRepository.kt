package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Repository

interface AccountRepository : Repository<Account, String>