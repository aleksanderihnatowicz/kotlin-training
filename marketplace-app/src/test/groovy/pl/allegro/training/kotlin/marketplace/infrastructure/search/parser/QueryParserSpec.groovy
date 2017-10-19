package pl.allegro.training.kotlin.marketplace.infrastructure.search.parser

import pl.allegro.training.kotlin.marketplace.infrastructure.search.Phrase
import spock.lang.Specification

class QueryParserSpec extends Specification {

    def parser = new QueryParser()

    def "should parse query with each type of phrase"() {
        when:
        def phrases = parser.parse(query)

        then:
        phrases.first() == phrase

        where:
        query     | phrase
        "phrase"  | Phrase.optional("phrase")
        "+phrase" | Phrase.required("phrase")
        "-phrase" | Phrase.forbidden("phrase")
    }

    def "should parse query with multiple phrases"() {
        given:
        def query = "+quick brown -fox"

        expect:
        parser.parse(query) == [
                // invoking companion object method with @JvmStatic
                Phrase.required("quick"),
                Phrase.optional("brown"),
                Phrase.forbidden("fox")
        ] as Set
    }

}
