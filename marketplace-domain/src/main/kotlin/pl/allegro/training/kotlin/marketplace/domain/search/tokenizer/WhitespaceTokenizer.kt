package pl.allegro.training.kotlin.marketplace.domain.search.tokenizer

class WhitespaceTokenizer : Tokenizer {
    private val whitespaceRegex = Regex("\\s+")

    override fun tokenize(text: String): Set<String> = text.split(whitespaceRegex).toSet()
}