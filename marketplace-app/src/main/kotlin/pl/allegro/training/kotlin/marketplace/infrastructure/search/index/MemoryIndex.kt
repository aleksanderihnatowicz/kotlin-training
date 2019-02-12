package pl.allegro.training.kotlin.marketplace.infrastructure.search.index

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId

class MemoryIndex : Index {
    private val store = HashMap<String, Set<DocumentId>>()

    override fun addToken(occurrence: Pair<String, DocumentId>) {
        val (token, docId) = occurrence
        store.compute(token) { _, documentIds ->
            if (documentIds == null) setOf(docId) else documentIds + docId
        }
    }

    override fun findToken(token: String): Set<DocumentId> = store.getOrDefault(token, emptySet())
}