package pl.allegro.training.kotlin.marketplace.domain.search

import pl.allegro.training.kotlin.marketplace.domain.search.index.Index
import pl.allegro.training.kotlin.marketplace.domain.search.parser.QueryParser


class Searcher(private val index: Index) {
    private val parser = QueryParser()

    fun search(queryText: String): List<DocumentId> {
        if (queryText.isEmpty()) throw EmptyQueryException()
        val query = parser.parse(queryText)
        val validDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.required }.map { it.value })
        val invalidDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.forbidden }.map { it.value })
        val optionalDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.optional }.map { it.value })
        // operation on sets with operators
        return (validDocs - invalidDocs).sortedWith(BoostingComparator(optionalDocs).reversed())
    }

    private fun Index.getDocumentsWithTokens(tokens: List<String>): Set<DocumentId> =
            tokens.flatMap { token -> this.getTokenOccurrences(token) }.toSet()
}

class EmptyQueryException : RuntimeException()

class BoostingComparator(private val boostedIds: Set<DocumentId>) : Comparator<DocumentId> {
    override fun compare(aDocId: DocumentId?, bDocId: DocumentId?): Int =
            boostedIds.contains(aDocId).compareTo(boostedIds.contains(bDocId))
}