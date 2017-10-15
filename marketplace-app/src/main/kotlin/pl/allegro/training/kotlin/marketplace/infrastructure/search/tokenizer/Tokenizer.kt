package pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer

interface Tokenizer {
    fun tokenize(text: String): Set<String>
}