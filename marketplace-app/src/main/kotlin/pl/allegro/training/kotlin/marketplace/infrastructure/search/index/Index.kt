package pl.allegro.training.kotlin.marketplace.infrastructure.search.index

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId

interface Index {
    fun addTokenOccurrence(occurrence: Pair<String, DocumentId>)
    fun getTokenOccurrences(token: String): Set<DocumentId>
}