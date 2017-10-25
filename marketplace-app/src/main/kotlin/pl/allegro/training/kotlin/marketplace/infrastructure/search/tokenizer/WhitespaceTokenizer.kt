package pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer

class WhitespaceTokenizer : Tokenizer {
    private val whitespaceRegex = Regex("\\s+")

    override fun tokenize(text: String): Set<String> = text
            // split zwraca listę a nie arraya
            .split(whitespaceRegex)
            .filterNot { it.isBlank() }
            .toSet()
}