package pl.allegro.training.kotlin.marketplace.domain.search

//class Query(phrases: Collection<Phrase>) : HashSet<Phrase>(phrases)
typealias Query = HashSet<Phrase>

// construction possibility blocked by private constructor - must use companion object
class Phrase private constructor(
        val value: String,
        val required: Boolean = false,
        val optional: Boolean = false,
        val forbidden: Boolean = false
) {
    // companion object
    // named parameters
    companion object {
        fun required(value: String) = Phrase(value, required = true)

        fun optional(value: String) = Phrase(value, optional = true)

        fun forbidden(value: String) = Phrase(value, forbidden = true)
    }
}
