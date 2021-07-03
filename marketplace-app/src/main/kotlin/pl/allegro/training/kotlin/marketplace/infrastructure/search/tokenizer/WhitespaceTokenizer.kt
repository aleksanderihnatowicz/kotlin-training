package pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer

class WhitespaceTokenizer : Tokenizer {
    private val whitespaceRegex = Regex("\\s+")

    override fun tokenize(text: String): Set<String> = text
        .split(whitespaceRegex)
        .filterNot { it.isBlank() }
        .map { it.lowercase() }
        .toSet()
}
