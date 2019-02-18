package pl.allegro.training.kotlin.marketplace.infrastructure.search.tokenizer

import spock.lang.Specification


class WhitespaceTokenizerSpec extends Specification {

    def tokenizer = new WhitespaceTokenizer()

    def "should split text with spaces into tokens"() {
        given:
        def text = "The quick brown fox jumps over the lazy dog"

        expect:
        tokenizer.tokenize(text).size() == 8
    }

    def "should return zero tokens for text containing spaces only"() {
        given:
        def text = "        "

        expect:
        tokenizer.tokenize(text).isEmpty()
    }

}
