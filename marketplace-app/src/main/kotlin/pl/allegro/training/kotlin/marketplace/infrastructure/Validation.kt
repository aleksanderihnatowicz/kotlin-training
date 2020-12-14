package pl.allegro.training.kotlin.marketplace.infrastructure

private val EMAIL_PATTERN = Regex("[a-zA-Z.]+@[a-zA-Z.]+")

fun isValidEmail(email: String): Boolean = email matches EMAIL_PATTERN
