package pl.allegro.training.kotlin.marketplace.infrastructure.repository

interface Identifiable<out T> {
    val id: T?
}