@file:JvmName("ValidationKt")

package pl.allegro.training.kotlin.marketplace.infrastructure

const val REGEX_PATTERN = "[a-zA-Z.]+@[a-zA-Z.]+"

fun isValidEmail(email: String) = email matches Regex(REGEX_PATTERN)
