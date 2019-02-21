package pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document

interface Indexer {

    // fun save(doc: Document)

    operator fun plusAssign(doc: Document)
}
