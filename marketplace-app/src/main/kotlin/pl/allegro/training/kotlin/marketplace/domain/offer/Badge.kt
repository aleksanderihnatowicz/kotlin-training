package pl.allegro.training.kotlin.marketplace.domain.offer

data class Badge(
    val freeShipping: Boolean = false,
    val bestseller: Boolean = false,
    val zeroInterest: Boolean = false
) {
    companion object {
        val NONE = Badge()
    }
}

/* class Badge private constructor(
    val freeShipping: Boolean = false,
    val bestseller: Boolean = false,
    val zeroInterest: Boolean = false
) {
    companion object {
        @JvmStatic
        fun freeShipping() = Badge(freeShipping = true)

        @JvmStatic
        fun bestseller() = Badge(bestseller = true)

        @JvmStatic
        fun zeroInterest() = Badge(zeroInterest = true)

        val NONE = Badge()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Badge

        if (freeShipping != other.freeShipping) return false
        if (bestseller != other.bestseller) return false
        if (zeroInterest != other.zeroInterest) return false

        return true
    }

    override fun hashCode(): Int {
        var result = freeShipping.hashCode()
        result = 31 * result + bestseller.hashCode()
        result = 31 * result + zeroInterest.hashCode()
        return result
    }
} */
