package pl.allegro.training.kotlin.marketplace.infrastructure

private const val REGEX_PATTERN = "[a-zA-Z.]+@[a-zA-Z.]+"

fun isValidEmail(email: String): Boolean = email matches Regex(REGEX_PATTERN)
