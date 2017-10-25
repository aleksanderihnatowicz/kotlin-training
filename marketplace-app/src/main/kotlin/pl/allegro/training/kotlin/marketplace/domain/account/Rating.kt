package pl.allegro.training.kotlin.marketplace.domain.account

// @Piotrek
// na koniec 1 czesci

class Rating(val likes: Int, val dislikes: Int) {
    // secondary constructor - must call primary
    constructor(data: Pair<Int, Int>) : this(data.first, data.second)

    companion object {
        val INITIAL = Rating(0, 0)
    }

    // virtual property - custom getter
    val likeRatio: Double
        get() = likes.toDouble() / (likes + dislikes).toDouble()

    // deconstruction contract
    operator fun component1() = likes

    operator fun component2() = dislikes
}