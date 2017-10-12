package pl.allegro.training.kotlin.marketplace.domain.misc

interface IdGenerator {
    fun getNextId(): String
}