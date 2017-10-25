package pl.allegro.training.kotlin.marketplace.infrastructure.search

import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.parser.QueryParser

typealias  Scorer = (docId: DocumentId) -> Double

// pokazać ekstractowanie typu do typealiasu za pomoca refactora
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
        // pokazac invoka
        val scorer = scorerFactory.invoke(optionalDocs)
        // operation on sets with operators
        // scorer as a high-order function
        return (validDocs - invalidDocs).sortedBy(scorer)
    }

    // przepisac na funkcje przyjmujaca predykat od phrase
    private fun Index.getDocumentsWithTokens(tokens: List<String>): Set<DocumentId> =
            // użycie referencji do funkcji ::funName
            tokens.flatMap(this::getTokenOccurrences).toSet()
}

class EmptyQueryException : RuntimeException()

// anonimowa funkcja
private val boostingScorerFactory: ScorerFactory = { optionalDocs ->
    // operator in
    { docId -> (docId in optionalDocs).asDouble() }
}

private fun Boolean.asDouble(): Double = if (this) 1.0 else 0.0
