package pl.allegro.training.kotlin.marketplace.infrastructure.search.parser

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Phrase
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Query
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.Tokenizer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

class QueryParser(private val tokenizer: Tokenizer = WhitespaceTokenizer()) {

    fun parse(query: String): Query {
        val phrases = tokenizer.tokenize(query).map { it.asPhrase() }
        return Query(phrases)
    }

    private fun String.asPhrase(): Phrase = when (this[0]) {
        '+'  -> Phrase.required(this.substring(1))
        '-'  -> Phrase.forbidden(this.substring(1))
        else -> Phrase.optional(this)
    }
}