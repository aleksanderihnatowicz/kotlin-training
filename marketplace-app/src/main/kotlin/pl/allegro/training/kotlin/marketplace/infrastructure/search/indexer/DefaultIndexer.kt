package pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.Tokenizer

class DefaultIndexer(private val index: Index, private val tokenizer: Tokenizer) : Indexer {

    override operator fun plusAssign(doc: Document) {
        val tokens = tokenizer.tokenize(doc.text)
        tokens.forEach {
            index.addToken(it to doc.id)
        }
    }
}