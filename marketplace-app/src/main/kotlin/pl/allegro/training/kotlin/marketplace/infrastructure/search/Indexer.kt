package pl.allegro.training.kotlin.marketplace.infrastructure.search

import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.Tokenizer


class Indexer(private val index: Index, private val tokenizer: Tokenizer) {

    fun add(doc: Document) {
        val tokens = tokenizer.tokenize(doc.text)
        tokens.forEach {
            index.addTokenOccurrence(it to doc.id)
        }
    }
}

