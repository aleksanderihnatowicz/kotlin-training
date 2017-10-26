package pl.allegro.training.kotlin.marketplace.infrastructure.search

import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.parser.QueryParser

typealias Scorer = (docId: DocumentId) -> Double

typealias ScorerFactory = (optionalDocs: Set<DocumentId>) -> Scorer

class Searcher(
        private val index: Index,
        private val scorerFactory: ScorerFactory = boostingScorerFactory
) {
    private val parser: QueryParser = QueryParser()

    fun search(queryText: String): List<DocumentId> {
        val query = parser.parse(queryText)
        if (query.isEmpty()) throw EmptyQueryException()

        val validDocs = index.getDocumentsWithPhrases(query.requiredPhrases)
        val invalidDocs = index.getDocumentsWithPhrases(query.forbiddenPhrases)
        val optionalDocs = index.getDocumentsWithPhrases(query.optionalPhrases)

        val scorer = scorerFactory.invoke(optionalDocs)

        return (validDocs - invalidDocs).sortedBy(scorer)
    }

    private fun Index.getDocumentsWithPhrases(phrases: List<Phrase>): Set<DocumentId> =
            phrases.map { it.value }.flatMap(this::getTokenOccurrences).toSet()

    private val Query.requiredPhrases: List<Phrase>
        get() = this.filter { it.required }

    private val Query.forbiddenPhrases: List<Phrase>
        get() = this.filter { it.forbidden }

    private val Query.optionalPhrases: List<Phrase>
        get() = this.filter { it.optional }
}

class EmptyQueryException : RuntimeException()

private val boostingScorerFactory: ScorerFactory = { optionalDocs ->
    { docId -> (docId in optionalDocs).asDouble() }
}

private fun Boolean.asDouble(): Double = if (this) 1.0 else 0.0
