package pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher

import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Phrase
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Query
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.Index
import pl.allegro.training.kotlin.marketplace.infrastructure.search.parser.QueryParser

class DefaultSearcher(private val index: Index) : Searcher {

    private val parser: QueryParser = QueryParser()

    override fun search(queryText: String): List<DocumentId> {
        if (queryText.isEmpty()) { return emptyList() }

        val query = parser.parse(queryText)

        val validDocs = index.findTokens(query.requiredPhrases)
        val invalidDocs = index.findTokens(query.forbiddenPhrases)
        val optionalDocs = index.findTokens(query.optionalPhrases)

        return (validDocs + optionalDocs - invalidDocs).sortedWith(PresenceComparator(optionalDocs))
    }

    private fun Index.findTokens(phrases: List<Phrase>): Set<DocumentId> =
        phrases.map { it.value }.flatMap(this::findToken).toSet()

    private val Query.requiredPhrases: List<Phrase>
        get() = this.filter { it.required }

    private val Query.forbiddenPhrases: List<Phrase>
        get() = this.filter { it.forbidden }

    private val Query.optionalPhrases: List<Phrase>
        get() = this.filter { it.optional }
}
