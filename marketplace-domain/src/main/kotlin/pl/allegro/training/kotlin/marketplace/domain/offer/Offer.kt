package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.domain.misc.IdGenerator
import pl.allegro.training.kotlin.marketplace.domain.misc.Repository
import pl.allegro.training.kotlin.marketplace.domain.search.Document
import pl.allegro.training.kotlin.marketplace.domain.search.DocumentId
import pl.allegro.training.kotlin.marketplace.domain.search.Indexer
import pl.allegro.training.kotlin.marketplace.domain.search.MemoryIndex
import pl.allegro.training.kotlin.marketplace.domain.search.Searcher
import pl.allegro.training.kotlin.marketplace.domain.search.WhitespaceTokenizer

data class Offer(
        override val id: String?,
        val sellerId: String?,
        val title: String,
        val description: String
) : Identifiable

class OfferService(private val offerRepository: OfferRepository, private val idGenerator: IdGenerator) {
    private val offerIndexer: Indexer
    private val offerSearcher: Searcher

    // complex initialization of fields
    init {
        val index = MemoryIndex()
        val tokenizer = WhitespaceTokenizer()
        offerIndexer = Indexer(index, tokenizer)
        offerSearcher = Searcher(index)
    }

    fun addOffer(offer: Offer, sellerId: String) {
        // data class generates copy() for us
        val persistent = offer.copy(id = idGenerator.getNextId(), sellerId = sellerId)
        offerRepository.save(persistent)
        // extension function for conversion
        offerIndexer.add(offer.asDocument())
    }

    // map + filterNotNull = mapNotNull
    fun findOffers(query: String): List<Offer> = offerSearcher.search(query).mapNotNull { offerRepository.findById(it.value) }

    // !! operator
    // string concatenation
    // let eases type conversion
    private fun Offer.asDocument(): Document = Document(this.id!!.let(::DocumentId), "$title $description")
}

// interface with property
interface Identifiable {
    val id: String?
}

// interface inheritance
interface OfferRepository : Repository<Offer, String>

// interface implementation
class MemoryOfferRepository : OfferRepository {
    private val offers = HashMap<String, Offer>()

    // pair syntax sugar
    // smart cast of offer id
    override fun save(entity: Offer) {
        if(entity.id == null) {
            throw InvalidOfferException()
        }
        offers += entity.id to entity
    }

    override fun findAll(): List<Offer> = offers.values.toList()

    // getting from map through get operator
    override fun findById(id: String): Offer? = offers[id]
}

class InvalidOfferException : RuntimeException()
