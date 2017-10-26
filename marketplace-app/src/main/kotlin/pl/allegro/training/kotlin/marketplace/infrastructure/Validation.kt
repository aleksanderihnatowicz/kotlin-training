package pl.allegro.training.kotlin.marketplace.infrastructure

private const val EMAIL_REGEX = "[a-zA-Z.]+@[a-zA-Z.]+"

fun isValidEmail(email: String): Boolean = email matches Regex(EMAIL_REGEX)
