package pl.allegro.training.kotlin.marketplace.domain.misc

// interface with property
interface Identifiable<out T> {
    val id: T?
}