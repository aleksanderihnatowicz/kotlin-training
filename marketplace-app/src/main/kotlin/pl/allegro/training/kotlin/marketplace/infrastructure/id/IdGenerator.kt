package pl.allegro.training.kotlin.marketplace.infrastructure.id

interface IdGenerator {
    fun getNextId(): String
}