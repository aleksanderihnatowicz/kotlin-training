package pl.allegro.training.kotlin.marketplace.infrastructure.repository

// interface with property
interface Identifiable<out T> {
    val id: T?
}