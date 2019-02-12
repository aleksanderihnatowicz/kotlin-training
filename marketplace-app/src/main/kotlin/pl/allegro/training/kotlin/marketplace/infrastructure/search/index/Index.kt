package pl.allegro.training.kotlin.marketplace.infrastructure.search.index

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId

interface Index {
    fun addToken(occurrence: Pair<String, DocumentId>)
    fun findToken(token: String): Set<DocumentId>
}