package pl.allegro.training.kotlin.marketplace.domain.offer


// interface implementation
class MemoryOfferRepository : OfferRepository {
    private val offers = HashMap<String, Offer>()

    // pair syntax sugar
    // smart cast of offer id
    override fun save(entity: Offer) {
        if(entity.id == null) {
            throw InvalidOfferException()
        }
        // setting map using [] operator
        offers[entity.id] =  entity
    }

    override fun findAll(): List<Offer> = offers.values.toList()

    // getting from map through get operator
    override fun findById(id: String): Offer? = offers[id]
}