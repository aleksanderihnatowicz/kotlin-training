package pl.allegro.training.kotlin.marketplace.infrastructure.search.parser

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Phrase
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Query
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

class QueryParser {
    private val tokenizer = WhitespaceTokenizer()

    // using let()'s lambda version - very readable code; alternative - constructor reference
    fun parse(query: String): Query = tokenizer.tokenize(query).map { it.asPhrase() }.let { Query(it) }

    // when expression
    // getting first character of string with [] operator
    private fun String.asPhrase(): Phrase = when (this.firstCharacter) {
        '+'  -> Phrase.required(this.substring(1))
        '-'  -> Phrase.forbidden(this.substring(1))
        else -> Phrase.optional(this)
    }

    // extension property
    private val String.firstCharacter
        get() = this[0]
}