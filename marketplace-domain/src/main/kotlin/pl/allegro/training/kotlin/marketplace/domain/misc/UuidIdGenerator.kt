package pl.allegro.training.kotlin.marketplace.domain.misc

import java.util.UUID

class UuidIdGenerator : IdGenerator {
    override fun getNextId(): String = UUID.randomUUID().toString()
}