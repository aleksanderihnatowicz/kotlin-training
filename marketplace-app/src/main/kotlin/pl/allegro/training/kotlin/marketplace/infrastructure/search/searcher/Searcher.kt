package pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId

interface Searcher {
    fun search(queryText: String): List<DocumentId>
}
