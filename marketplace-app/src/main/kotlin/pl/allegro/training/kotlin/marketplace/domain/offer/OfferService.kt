package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher.EmptyQueryException
import pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer.Indexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher.Searcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.MemoryIndex
import pl.allegro.training.kotlin.marketplace.infrastructure.search.indexer.DefaultIndexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.searcher.DefaultSearcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

class OfferService(
    private val offerRepository: OfferRepository,
    private val idGenerator: IdGenerator
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
        val persistent = offer.copy(id = offer.id ?: idGenerator.getNextId())
        offerRepository.save(persistent)
        indexer += persistent.asDocument()
        return persistent
    }

    fun findOffers(query: String): List<Offer> = try {
        searcher.search(query).mapNotNull { offerRepository.findById(it.value) }
    } catch (e: EmptyQueryException) {
        emptyList()
    }

    private fun Offer.asDocument(): Document = Document(DocumentId(this.id!!), "$title $description")
}