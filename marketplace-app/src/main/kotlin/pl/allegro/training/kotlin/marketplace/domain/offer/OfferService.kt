package pl.allegro.training.kotlin.marketplace.domain.offer

import pl.allegro.training.kotlin.marketplace.infrastructure.id.IdGenerator
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Document
import pl.allegro.training.kotlin.marketplace.infrastructure.search.DocumentId
import pl.allegro.training.kotlin.marketplace.infrastructure.search.EmptyQueryException
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Indexer
import pl.allegro.training.kotlin.marketplace.infrastructure.search.Searcher
import pl.allegro.training.kotlin.marketplace.infrastructure.search.index.MemoryIndex
import pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer.WhitespaceTokenizer

// @Piotrek
// 6a. Dodanie oferty - dochodzi indeksowanie, zapoznanie z api searcha, pokazanie inicjalizacji w init {}
// 6b. extension function i komentarze nad

// @Konrad
// 6c. Pokazać operator safe call null.toString() vs null?.toString()
// 6d. Kolekcje: mutowalność, nie trzeba importować kolekcji, funkcje mapujące, factorki do kolekcji

// @Piotrek
// 3 cześć
// 8. Pair and Triple, podkreślenie, operator + na secie
// Omówienie MemoryIndex, Indexer, Tokenizer, Searcher, QueryParser + testy, Phrase
// Odpalenie przykładu w main
// 8' (po tenisie). Delegaty na przykładzie AuditableMemoryIndex

class OfferService(
        private val offerRepository: OfferRepository,
        private val idGenerator: IdGenerator
) {
    private val offerIndexer: Indexer
    private val offerSearcher: Searcher

    // complex initialization of fields
    init {
        val index = MemoryIndex()
        val tokenizer = WhitespaceTokenizer()
        offerIndexer = Indexer(index, tokenizer)
        offerSearcher = Searcher(index)
    }

    fun addOffer(offer: Offer): Offer {
        // data class generates copy() for us
        val persistent = offer.copy(id = offer.id ?: idGenerator.getNextId())
        offerRepository.save(persistent)
        // extension function for conversion
        offerIndexer += persistent.asDocument() //zmienić na funkcje bezoperatorowa
        return persistent
    }

    // map + filterNotNull = mapNotNull
    // try-catch is an expression
    // last expression returns, no need to use return instruction
    fun findOffers(query: String): List<Offer> = try {
        // najpierw napisać za pomocą petli for, omówic petle
        offerSearcher.search(query).mapNotNull { offerRepository.findById(it.value) }
    } catch (e: EmptyQueryException) {
        emptyList()
    }

    // !! operator
    // string concatenation
    // let eases type conversion - na bloku i przekonwertowac na referencje
    // referencja do funkcji (konstruktor)
    private fun Offer.asDocument(): Document = Document(this.id!!.let(::DocumentId), "$title $description")
}