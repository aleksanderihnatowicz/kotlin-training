package pl.allegro.training.kotlin.marketplace.infrastructure.search

import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.parser.QueryParser

typealias  Scorer = (docId: DocumentId) -> Double

typealias ScorerFactory = (optionalDocs: Set<DocumentId>) -> Scorer

class Searcher(
    private val index: Index,
    private val scorerFactory: ScorerFactory = boostingScorerFactory,
    private val parser: QueryParser = QueryParser()
) {

    fun search(queryText: String): List<DocumentId> {
        val query = parser.parse(queryText)
        if (query.isEmpty()) throw EmptyQueryException()
        val validDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.required }.map { it.value })
        val invalidDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.forbidden }.map { it.value })
        val optionalDocs = index.getDocumentsWithTokens(query.filter { phrase -> phrase.optional }.map { it.value })
        val scorer = scorerFactory.invoke(optionalDocs)
        // operation on sets with operators
        // scorer as a high-order function
        return (validDocs - invalidDocs).sortedBy(scorer)
    }

    private fun Index.getDocumentsWithTokens(tokens: List<String>): Set<DocumentId> =
            tokens.flatMap { token -> this.getTokenOccurrences(token) }.toSet()
}

class EmptyQueryException : RuntimeException()

private val boostingScorerFactory: ScorerFactory = { optionalDocs ->
    { docId -> (docId in optionalDocs).asDouble() }
}

private fun Boolean.asDouble(): Double = if (this) 1.0 else 0.0
