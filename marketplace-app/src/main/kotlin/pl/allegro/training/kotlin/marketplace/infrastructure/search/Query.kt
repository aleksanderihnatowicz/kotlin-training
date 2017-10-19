package pl.allegro.training.kotlin.marketplace.infrastructure.search

//class Query(phrases: Collection<Phrase>) : HashSet<Phrase>(phrases)
typealias Query = HashSet<Phrase>

// construction possibility blocked by private constructor - must use companion object
// we want private constructor so we cannot use data class
class Phrase private constructor(
        val value: String,
        val required: Boolean = false,
        val optional: Boolean = false,
        val forbidden: Boolean = false
) {
    // companion object
    // named parameters
    companion object {
        @JvmStatic
        fun required(value: String) = Phrase(value, required = true)

        @JvmStatic
        fun optional(value: String) = Phrase(value, optional = true)

        @JvmStatic
        fun forbidden(value: String) = Phrase(value, forbidden = true)
    }

    // generated equals and hashCode
    // smart cast makes equals very concise
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Phrase

        if (value != other.value) return false
        if (required != other.required) return false
        if (optional != other.optional) return false
        if (forbidden != other.forbidden) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + required.hashCode()
        result = 31 * result + optional.hashCode()
        result = 31 * result + forbidden.hashCode()
        return result
    }
}
