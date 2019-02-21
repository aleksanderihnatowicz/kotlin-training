package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.config.SearchConfiguration
import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer.Indexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher.Searcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.MemoryIndex
import pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer.DefaultIndexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher.DefaultSearcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

class OfferService(
    private val repository: OfferRepository,
    private val idGenerator: IdGenerator,
    private val configuration: SearchConfiguration
) {
    private val indexer: Indexer
    private val searcher: Searcher

    init {
        val index = MemoryIndex()
        val tokenizer = WhitespaceTokenizer()
        indexer = DefaultIndexer(index, tokenizer)
        searcher = DefaultSearcher(index)
    }

    fun addOffer(offer: Offer): Offer {
        val offerWithId = offer.copy(id = offer.id ?: idGenerator.getNextId())
        repository.save(offerWithId)
        indexer += offerWithId.asDocument()
        return offerWithId
    }

    fun findOffers(query: String): List<Offer> {
        ensureQueryNotEmpty(query)

        val offers = ArrayList<Offer>()

        for(result in searcher.search(query)) {
            val offer = repository.findById(result.value)
            offer?.let { offers.add(it) }
        }

        return offers
    }

    private fun ensureQueryNotEmpty(query: String) {
        if (query.isEmpty() && !configuration.allowEmptyQuery) {
            throw EmptyQueryException()
        }
    }

    private fun Offer.asDocument(): Document = Document(DocumentId(this.id!!), "$title $description")
}
