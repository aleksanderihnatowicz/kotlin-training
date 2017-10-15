package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.domain.misc.IdGenerator
import pl.allegro.training.kotlin.marketplace.domain.search.Document
import pl.allegro.training.kotlin.marketplace.domain.search.DocumentId
import pl.allegro.training.kotlin.marketplace.domain.search.Indexer
import pl.allegro.training.kotlin.marketplace.domain.search.MemoryIndex
import pl.allegro.training.kotlin.marketplace.domain.search.Searcher
import pl.allegro.training.kotlin.marketplace.domain.search.WhitespaceTokenizer
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
    fun findOffers(query: String): List<Offer> = offerSearcher.search(query).mapNotNull { offerRepository.findById(it.value) }

    // !! operator
    // string concatenation
    // let eases type conversion
    private fun Offer.asDocument(): Document = Document(this.id!!.let(::DocumentId), "$title $description")
}