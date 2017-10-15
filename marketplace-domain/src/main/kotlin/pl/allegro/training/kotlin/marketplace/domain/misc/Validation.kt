package pl.allegro.training.kotlin.marketplace.domain.misc

// compile-time constant - only primitive types + String
const val EMAIL_REGEX = "[a-zA-Z.]+@[a-zA-Z.]+"

// infix notation of matches
// if private, visible only in this file
fun isValidEmail(email: String): Boolean = email matches Regex(EMAIL_REGEX)
