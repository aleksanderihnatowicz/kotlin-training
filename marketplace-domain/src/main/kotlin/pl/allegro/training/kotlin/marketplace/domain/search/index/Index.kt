package pl.allegro.training.kotlin.marketplace.domain.search.index

import pl.allegro.training.kotlin.marketplace.domain.search.DocumentId

interface Index {
    fun addTokenOccurrence(occurrence: Pair<String, DocumentId>)
    fun getTokenOccurrences(token: String): Set<DocumentId>
}