package pl.allegro.training.kotlin.marketplace

class InvalidAccountException(email: String): RuntimeException("Unable to create account with email: $email")
