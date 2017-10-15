package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.EmptyQueryException
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Indexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Searcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.MemoryIndex
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer
import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import java.math.BigDecimal

class OfferService(private val offerRepository: OfferRepository, private val idGenerator: IdGenerator) {
    private val offerIndexer: Indexer
    private val offerSearcher: Searcher

    // complex initialization of fields
    init {
        val index = MemoryIndex()
        val tokenizer = WhitespaceTokenizer()
        offerIndexer = Indexer(index, tokenizer)
        offerSearcher = Searcher(index)
        addOffer(Offer(null, "seller-id", "title", "desc", BigDecimal(12.89)))
    }

    fun addOffer(offer: Offer): Offer {
        // data class generates copy() for us
        val persistent = offer.copy(id = idGenerator.getNextId())
        offerRepository.save(persistent)
        // extension function for conversion
        offerIndexer.add(persistent.asDocument())
        return persistent
    }

    // map + filterNotNull = mapNotNull
    // try-catch is an expression
    fun findOffers(query: String): List<Offer> = try {
        offerSearcher.search(query).mapNotNull { offerRepository.findById(it.value) }
    } catch (e: EmptyQueryException) {
        emptyList()
    }

    // !! operator
    // string concatenation
    // let eases type conversion
    private fun Offer.asDocument(): Document = Document(this.id!!.let(::DocumentId), "$title $description")
}