package pl.allegro.training.kotlin.marketplace.domain.search.tokenizer

interface Tokenizer {
    fun tokenize(text: String): Set<String>
}