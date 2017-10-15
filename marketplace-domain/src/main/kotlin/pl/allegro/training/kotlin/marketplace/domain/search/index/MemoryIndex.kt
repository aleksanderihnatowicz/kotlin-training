package pl.allegro.training.kotlin.marketplace.domain.search.index

import pl.allegro.training.kotlin.marketplace.domain.search.DocumentId

class MemoryIndex : Index {
    private val store = HashMap<String, Set<DocumentId>>()

    override fun addTokenOccurrence(occurrence: Pair<String, DocumentId>) {
        val (token, docId) = occurrence
        // omitting unwanted arguments with _
        // if one-liner
        store.compute(token) { _, documentIds ->
            if (documentIds == null) mutableSetOf(docId) else documentIds + docId
        }
    }

    override fun getTokenOccurrences(token: String): Set<DocumentId> {
        // emptySet()
        return store.getOrDefault(token, emptySet())
    }
}