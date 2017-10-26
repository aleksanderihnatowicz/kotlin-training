package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.EmptyQueryException
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Indexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Searcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.MemoryIndex
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

class OfferService(
        private val offerRepository: OfferRepository,
        private val idGenerator: IdGenerator
) {
    private val offerIndexer: Indexer
    private val offerSearcher: Searcher

    init {
        val index = MemoryIndex()
        val tokenizer = WhitespaceTokenizer()
        offerIndexer = Indexer(index, tokenizer)
        offerSearcher = Searcher(index)
    }

    fun addOffer(offer: Offer): Offer {
        val persistent = offer.copy(id = offer.id ?: idGenerator.getNextId())
        offerRepository.save(persistent)
        offerIndexer += persistent.asDocument()
        return persistent
    }

    fun findOffers(query: String): List<Offer> = try {
        offerSearcher.search(query).mapNotNull { offerRepository.findById(it.value) }
    } catch (e: EmptyQueryException) {
        emptyList()
    }

    private fun Offer.asDocument(): Document = Document(DocumentId(this.id!!), "$title $description")
}