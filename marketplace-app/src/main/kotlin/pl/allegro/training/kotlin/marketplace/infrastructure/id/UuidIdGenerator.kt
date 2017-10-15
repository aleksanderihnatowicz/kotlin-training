package pl.allegro.training.kotlin.marketplace.infrastructure.id

import java.util.UUID

class UuidIdGenerator : IdGenerator {
    override fun getNextId(): String = UUID.randomUUID().toString()
}