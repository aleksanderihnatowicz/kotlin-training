package pl.allegro.training.kotlin.marketplace.domain.account

class Rating(val likes: Int, val dislikes: Int) {
    constructor(data: Pair<Int, Int>) : this(data.first, data.second)

    companion object {
        val INITIAL = Rating(0, 0)
    }

    val likeRatio: Double
        get() = likes.toDouble() / (likes + dislikes).toDouble()

    operator fun component1() = likes

    operator fun component2() = dislikes
}